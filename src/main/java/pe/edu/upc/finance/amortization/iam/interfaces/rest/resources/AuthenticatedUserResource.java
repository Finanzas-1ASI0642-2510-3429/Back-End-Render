package pe.edu.upc.finance.amortization.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}