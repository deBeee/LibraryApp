package org.example.core;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.database.UserDAO;
import org.example.model.User;

public class Authenticator {
    final UserDAO userDAO = UserDAO.getInstance();
    private User loggedUser = null;
    private final String seed = "c5H0t1^%%Xe4i6f5eKFkM4WQ&ZOwSXwmO1FvMZ5q";
    private static final Authenticator instance = new Authenticator();

    private Authenticator() {

    }
    public void authenticate(User user) {
        User userFromDB = this.userDAO.findByLogin(user.getLogin());
        if(userFromDB != null &&
                userFromDB.getPassword().equals(
                        DigestUtils.md5Hex(user.getPassword() + this.seed))) {
            this.loggedUser = userFromDB;
        }
    }

    public static Authenticator getInstance() {
        return instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public String getSeed() {
        return seed;
    }
}
