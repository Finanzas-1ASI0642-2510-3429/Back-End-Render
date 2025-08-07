package pe.edu.upc.finance.amortization.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.edu.upc.finance.amortization.iam.application.internal.outboundservices.ExternalProfileService;
import pe.edu.upc.finance.amortization.iam.application.internal.outboundservices.hashing.HashingService;
import pe.edu.upc.finance.amortization.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.upc.finance.amortization.iam.domain.model.aggregates.User;
import pe.edu.upc.finance.amortization.iam.domain.model.commands.SignInCommand;
import pe.edu.upc.finance.amortization.iam.domain.model.commands.SignUpCommand;
import pe.edu.upc.finance.amortization.iam.domain.services.UserCommandService;
import pe.edu.upc.finance.amortization.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.edu.upc.finance.amortization.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final ExternalProfileService externalProfileService;

    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  TokenService tokenService,
                                  RoleRepository roleRepository,
                                  ExternalProfileService externalProfileService) {

        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.externalProfileService = externalProfileService;
        this.roleRepository = roleRepository;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());

        if (user.isEmpty()) {
            var profileId = externalProfileService.fetchProfileIdByEmail(command.username());
            if (profileId.isPresent()) {
                user = userRepository.findByProfileId(profileId.get());
            }
        }

        // Check if a user was found by either username or email
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with email or username");
        }

        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        var token = tokenService.generateToken(user.get().getUsername());

        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        // 1. Validate if username already exists
        if (userRepository.existsByUsername(command.username())) {
            throw new RuntimeException("Username: " + command.username() + " already exists.");
        }

        // 2. Validate if email already exists and is associated with an existing user
        var existingProfileId = externalProfileService.fetchProfileIdByEmail(command.email());
        if (existingProfileId.isPresent()) {
            // If a profile with this email exists, check if there's a user linked to it.
            // If there is, it means the email is already in use by an existing user.
            userRepository.findByProfileId(existingProfileId.get()).ifPresent(user -> {
                throw new RuntimeException("User with email " + command.email() + " already exists.");
            });
            // If a profile exists but no user is linked (unlikely in a clean state, but possible edge case),
            // we'd still consider the email "taken" for a new sign-up.
            // Depending on your exact domain, you might want to throw an exception here too,
            // or allow linking to an "orphaned" profile if that's a valid scenario.
            // For now, we'll assume a profile with an email means it's in use for user creation.
            throw new RuntimeException("Profile with email " + command.email() + " already exists, but is not linked to a user. Cannot proceed with new user creation.");
        }

        // 3. If both username and email are unique for a new user, proceed to create profile
        var newProfileId = externalProfileService.createProfile(
                command.firstName(),
                command.lastName(),
                command.email(),
                command.street(),
                command.number(),
                command.city(),
                command.postalCode(),
                command.country());

        // 4. If profile creation fails (e.g., due to external service issues), throw an exception
        if (newProfileId.isEmpty()) {
            throw new IllegalArgumentException("Unable to create profile.");
        }

        // 5. Fetch and validate roles
        var roles = command.roles().stream()
                .map(role ->
                        roleRepository.findByName(role.getName())
                                .orElseThrow(() -> new RuntimeException("Role name not found: " + role.getName()))) // Added role name for clarity
                .toList();

        // 6. Create the new user
        var user = new User(command.username(), hashingService.encode(command.password()), roles, newProfileId.get());

        // 7. Save the new user
        userRepository.save(user);

        // 8. Return the created user
        return userRepository.findByUsername(command.username());
    }
}