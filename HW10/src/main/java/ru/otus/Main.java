package ru.otus;

import ru.otus.Handlers.UserInsertExecuteHandler;
import ru.otus.Handlers.UserSelectExecuteHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {

        DbConnector dbConnector = new DbConnector();
        dbConnector.registerDriver("com.mysql.cj.jdbc.Driver");
        Connection connection = dbConnector.getConnection("localhost", "3306", "root", "root");

        DataSet user1 = new UserDataSet("Petr", 25);
        DataSet user2 = new UserDataSet("Fedor", 33);
        DataSet user3 = new UserDataSet("Vasya", 22);

        Executor executor = new Executor(connection);
        executor.execUpdate(new UserInsertExecuteHandler(user1));
        executor.execUpdate(new UserInsertExecuteHandler(user2));
        executor.execUpdate(new UserInsertExecuteHandler(user3));

        String query = "SELECT * FROM hw10.user WHERE id = " + 30;
        UserDataSet userX = (UserDataSet) executor.execSelect(query, new UserSelectExecuteHandler());
        System.out.println("Name = " + userX.getName() + ", Age = " + userX.getAge());

    }
}
