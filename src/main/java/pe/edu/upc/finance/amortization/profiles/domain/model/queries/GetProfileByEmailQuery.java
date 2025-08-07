package pe.edu.upc.finance.amortization.profiles.domain.model.queries;

import pe.edu.upc.finance.amortization.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}