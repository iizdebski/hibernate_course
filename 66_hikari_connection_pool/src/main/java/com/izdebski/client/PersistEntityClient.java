package com.izdebski.client;

import com.izdebski.dao.UserDAO;
import com.izdebski.entities.User;

public class PersistEntityClient {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();
        User user = getUser();
        userDAO.createUser(user);
    }

    private static User getUser() {
        User user = new User();
        user.setFirstName("Sean");
        user.setLastName("Connery");
        return user;
    }
}
