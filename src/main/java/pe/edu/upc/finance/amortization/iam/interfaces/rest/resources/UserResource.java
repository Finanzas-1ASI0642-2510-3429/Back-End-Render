package pe.edu.upc.finance.amortization.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String username, List<String> roles, long profileId) {
}