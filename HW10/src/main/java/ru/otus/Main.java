package ru.otus;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException {

        DbConnector dbConnector = new DbConnector();
        dbConnector.registerDriver("com.mysql.cj.jdbc.Driver");
        Connection connection = dbConnector.getConnection("localhost", "3306", "root", "root");

        UserDataSet user1 = new UserDataSet();
        user1.setName("Petr");
        user1.setAge(25);
        UserDataSet user2 = new UserDataSet();
        user2.setName("Fedor");
        user2.setAge(33);
        UserDataSet user3 = new UserDataSet();
        user3.setName("Vasya");
        user3.setAge(22);

        Executor executor = new Executor(connection);
        executor.save(user1);
        executor.save(user2);
        executor.save(user3);

        UserDataSet userX = (UserDataSet) executor.load(UserDataSet.class, 66);
        System.out.println("Name = " + userX.getName() + ", Age = " + userX.getAge());

    }
}
