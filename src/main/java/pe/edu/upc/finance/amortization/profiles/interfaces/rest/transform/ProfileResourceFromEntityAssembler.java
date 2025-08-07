package pe.edu.upc.finance.amortization.profiles.interfaces.rest.transform;

import pe.edu.upc.finance.amortization.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.finance.amortization.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmailAddress(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getCity(),
                entity.getPostalCode(),
                entity.getCountry());
    }
}