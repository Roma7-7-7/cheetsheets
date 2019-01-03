package cheetsheets.dk.security;

import cheetsheets.dk.core.User;
import cheetsheets.dk.service.UserService;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public BasicAuthenticator(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) {
        final Optional<User> o = this.userService.getUserByEmail(basicCredentials.getUsername());
        if (!o.isPresent()) {
            return Optional.empty();
        }

        final User user = o.get();
        if (!this.passwordEncoder.matches(basicCredentials.getPassword(), user.getPassword())) {
            return Optional.empty();
        }

        return o;
    }
}
