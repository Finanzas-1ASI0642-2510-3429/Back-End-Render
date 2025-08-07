package pe.edu.upc.finance.amortization.iam.interfaces.rest.transform;

import pe.edu.upc.finance.amortization.iam.domain.model.commands.SignInCommand;
import pe.edu.upc.finance.amortization.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}