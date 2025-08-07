package pe.edu.upc.finance.amortization.profiles.application.internal.commandservices;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.upc.finance.amortization.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.finance.amortization.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.finance.amortization.profiles.domain.model.commands.DeleteProfileCommand;
import pe.edu.upc.finance.amortization.profiles.domain.model.commands.UpdateProfileCommand;
import pe.edu.upc.finance.amortization.profiles.domain.model.valueobjects.EmailAddress;
import pe.edu.upc.finance.amortization.profiles.domain.services.ProfileCommandService;
import pe.edu.upc.finance.amortization.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(
            ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public Optional<Profile> handle(CreateProfileCommand command) {
        // 1. Validación básica del comando
        if (command == null) {
            throw new IllegalArgumentException("CreateProfileCommand cannot be null");
        }

        // 2. Validar email único
        var emailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(emailAddress).ifPresent(profile -> {
            throw new IllegalArgumentException("Profile with email " + command.email() + " already exists");
        });

        // 3. Crear y guardar el perfil
        var profile = new Profile(command);
        var savedProfile = profileRepository.save(profile);

        return Optional.of(savedProfile);
    }

    @Override
    @Transactional
    public Optional<Profile> handle(UpdateProfileCommand command) {
        // 1. Validación básica del comando
        if (command == null) {
            throw new IllegalArgumentException("UpdateProfileCommand cannot be null.");
        }

        // 2. Buscar el perfil existente por su ID
        // profileId() asumo que es un UUID o Long para el ID del Profile
        var existingProfileOptional = profileRepository.findById(command.profileId());

        if (existingProfileOptional.isEmpty()) {
            throw new IllegalArgumentException("Profile with ID " + command.profileId() + " not found.");
        }

        var profileToUpdate = existingProfileOptional.get();

        // 3. Validar que el email sea único y no pertenezca a otro perfil
        // Si el email no ha cambiado, no necesitamos validarlo.
        var newEmailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(newEmailAddress).ifPresent(existingProfileWithSameEmail -> {
            // Si encontramos un perfil con el mismo email Y NO ES el perfil que estamos actualizando
            if (!existingProfileWithSameEmail.getId().equals(profileToUpdate.getId())) {
                throw new IllegalArgumentException("Profile with email " + command.email() + " already exists for another profile.");
            }
        });

        // 4. Actualizar los datos del perfil (en el Aggregate Root)
        // El Aggregate Root (Profile) es quien conoce su propia lógica de negocio para actualizarse.
        // Asumo un método 'update' en la clase Profile.
        profileToUpdate.updateProfile(command);

        // 5. Guardar el perfil actualizado
        // Spring Data JPA detectará los cambios en el objeto gestionado y los persistirá.
        var updatedProfile = profileRepository.save(profileToUpdate);

        return Optional.of(updatedProfile);
    }

    @Override
    @Transactional
    public boolean handle(DeleteProfileCommand command) {
        // 1. Validación básica del comando
        if (command == null) {
            throw new IllegalArgumentException("DeleteProfileCommand cannot be null.");
        }

        // 2. Verificar si el perfil existe antes de intentar eliminarlo
        // command.profileId() asumo que es un UUID o Long para el ID del Profile
        if (!profileRepository.existsById(command.profileId())) {
            // Opcional: Podrías lanzar una excepción si es un error que no exista,
            // pero si la operación es "borrar si existe", retornar false es válido.
            return false;
        }

        // 3. Eliminar el perfil
        // Asegúrate de que tu repositorio de JPA tenga un método deleteById.
        profileRepository.deleteById(command.profileId());

        // 4. Confirmar la eliminación (opcional, pero buena práctica)
        // Verificamos si realmente ya no existe después del intento de eliminación.
        return !profileRepository.existsById(command.profileId());
    }
}