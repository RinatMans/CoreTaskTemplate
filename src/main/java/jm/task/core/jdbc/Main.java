package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User user1 = new User("Ринат","Мансуров", (byte) 36);
        User user2 = new User("Любовь","Мансурова", (byte) 35);
        User user3 = new User("Аделина","Мансурова", (byte) 11);
        User user4 = new User("Лилиана","Мансурова", (byte) 9);

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        userService.removeUserById(2);
        for (User element : userService.getAllUsers()) {
            System.out.println(element);
        }
       // userService.cleanUsersTable();
        //userService.dropUsersTable();

    }
}

