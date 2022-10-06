package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    private static UserService userService;

    public Main() {
        try {
            userService = new UserServiceImpl();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Vova", "Putin", (byte) 78);
        userService.saveUser("Trump", "Donald", (byte) 74);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
