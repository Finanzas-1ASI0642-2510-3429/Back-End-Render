package pe.edu.upc.finance.amortization.profiles.interfaces.rest.transform;

import pe.edu.upc.finance.amortization.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.finance.amortization.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(), resource.lastName(),
                resource.email(), resource.street(), resource.number(), resource.city(),
                resource.postalCode(), resource.country());
    }
}