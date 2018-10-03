package cheetsheets.dk.service;

import cheetsheets.dk.core.Role;
import cheetsheets.dk.core.User;
import cheetsheets.dk.db.UserDAO;
import com.google.common.collect.Multimap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getUsers() {
        return this.userDAO.getUsers().asMap().entrySet().stream()
                .map(e -> e.getKey().setRoles(new HashSet<>(e.getValue())))
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }

    public Optional<User> getUserByEmail(String email) {
        final Multimap<User, Role> result = this.userDAO.getUserByEmail(email);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        assert result.size() == 1;

        final User user = result.keys().iterator().next();
        user.setRoles(result.get(user));

        return Optional.of(user);
    }

}
