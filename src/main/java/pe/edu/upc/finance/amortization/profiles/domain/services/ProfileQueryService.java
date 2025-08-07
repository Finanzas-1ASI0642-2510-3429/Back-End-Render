package pe.edu.upc.finance.amortization.profiles.domain.services;

import pe.edu.upc.finance.amortization.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.finance.amortization.profiles.domain.model.queries.GetAllProfilesQuery;
import pe.edu.upc.finance.amortization.profiles.domain.model.queries.GetProfileByEmailQuery;
import pe.edu.upc.finance.amortization.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Profile> handle(GetProfileByIdQuery query);

    List<Profile> handle(GetAllProfilesQuery query);
}