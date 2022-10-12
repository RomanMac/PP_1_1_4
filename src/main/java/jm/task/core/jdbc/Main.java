package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;


public class Main {
    private static UserDao userService = null;

    public Main() {
        userService = new UserDaoHibernateImpl();
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
