package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    private final static UserService userService;

    static {
        try {
            userService = new UserServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Jo", "Biden", (byte) 78);
        userService.saveUser("Trump", "Donald", (byte) 74);
        userService.saveUser("Barack", "Obama", (byte) 59);
        userService.saveUser("Jord", "Bush", (byte) 74);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
