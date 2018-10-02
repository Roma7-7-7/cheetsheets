package cheetsheets.dk.service;

import cheetsheets.dk.core.User;
import cheetsheets.dk.db.UserDAO;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getUsers() {
        return this.userDAO.getUsers();
    }

}
