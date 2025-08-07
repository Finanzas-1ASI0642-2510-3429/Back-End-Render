package pe.edu.upc.finance.amortization.iam.domain.services;

import pe.edu.upc.finance.amortization.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}