package pe.edu.upc.finance.amortization.profiles.domain.services;

import pe.edu.upc.finance.amortization.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.finance.amortization.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.finance.amortization.profiles.domain.model.commands.DeleteProfileCommand;
import pe.edu.upc.finance.amortization.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);

    Optional<Profile> handle(UpdateProfileCommand command);

    boolean handle(DeleteProfileCommand command);
}
