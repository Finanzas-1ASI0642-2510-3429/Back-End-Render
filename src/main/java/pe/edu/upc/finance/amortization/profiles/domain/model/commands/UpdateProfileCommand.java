package pe.edu.upc.finance.amortization.profiles.domain.model.commands;

public record UpdateProfileCommand(Long profileId, String firstName, String lastName, String email,
                                   String street, String number, String city,
                                   String postalCode, String country) {
}
