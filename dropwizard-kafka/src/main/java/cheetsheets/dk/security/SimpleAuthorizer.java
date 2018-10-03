package cheetsheets.dk.security;

import cheetsheets.dk.core.Role;
import cheetsheets.dk.core.User;
import io.dropwizard.auth.Authorizer;

import java.util.Objects;

public class SimpleAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles().stream().map(Role::getName).anyMatch(r -> Objects.equals(r, role));
    }
}
