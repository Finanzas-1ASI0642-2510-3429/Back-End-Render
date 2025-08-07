package pe.edu.upc.finance.amortization.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(
        String username,
        String password,
        List<String> roles,
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country) {
}