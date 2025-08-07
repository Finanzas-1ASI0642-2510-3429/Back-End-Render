package pe.edu.upc.finance.amortization.iam.domain.model.commands;

import pe.edu.upc.finance.amortization.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(
        String username,
        String password,
        List<Role> roles,
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country) {
}
