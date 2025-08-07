package pe.edu.upc.finance.amortization.iam.interfaces.acl;

import io.jsonwebtoken.lang.Strings;
import org.springframework.stereotype.Service;
import pe.edu.upc.finance.amortization.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.finance.amortization.iam.domain.model.queries.GetUserByUsernameQuery;
import pe.edu.upc.finance.amortization.iam.domain.services.UserCommandService;
import pe.edu.upc.finance.amortization.iam.domain.services.UserQueryService;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other
 *     bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */
@Service
public class IamContextFacade {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public IamContextFacade(UserCommandService userCommandService,
                            UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Fetches the id of the user with the given username.
     * @param username The username of the user.
     * @return The id of the user.
     */
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty())
            return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the username of the user with the given id.
     * @param userId The id of the user.
     * @return The username of the user.
     */
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty())
            return Strings.EMPTY;
        return result.get().getUsername();
    }

    /**
     * Validates if a user exists with the specified ID
     * @param userId The user ID to validate
     * @return true if a user exists with this ID, false otherwise
     * @throws IllegalArgumentException if userId is null
     */
    public boolean existsUserByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        try {
            var query = new GetUserByIdQuery(userId);
            return userQueryService.handle(query).isPresent();
        } catch (Exception e) {
            // Log the exception if needed
            return false;
        }
    }
}