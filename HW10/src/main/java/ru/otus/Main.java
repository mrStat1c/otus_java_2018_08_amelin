package ru.otus;

import ru.otus.Handlers.UserInsertExecuteHandler;
import ru.otus.Handlers.UserSelectExecuteHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
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
        executor.execUpdate(DbQueries.USER_INSERT, new UserInsertExecuteHandler((UserDataSet) user1));
        executor.execUpdate(DbQueries.USER_INSERT, new UserInsertExecuteHandler((UserDataSet) user2));
        executor.execUpdate(DbQueries.USER_INSERT, new UserInsertExecuteHandler((UserDataSet) user3));

        UserDataSet userX = (UserDataSet) executor.execSelect(DbQueries.USER_SELECT, new UserSelectExecuteHandler(30));
        System.out.println("Name = " + userX.getName() + ", Age = " + userX.getAge());

        // В задании предполагалось использование reflection. Т.е. обход полей класса и отображение их на параметры
        // запроса при вставке и отображение полей запроса на поля объекта при чтении.

        //Смысл Executor в том, что он только выполняет запросы, а результат обрабатывает переданный в него handler
    }
}
