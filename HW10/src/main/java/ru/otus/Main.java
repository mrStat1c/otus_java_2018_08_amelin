package ru.otus;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, SQLException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {


        DataSet user1 = new UserDataSet("Petr", 25);
        DataSet user2 = new UserDataSet("Fedor", 33);
        DataSet user3 = new UserDataSet("Vasya", 22);

        Executor executor = new Executor();
        executor.safe(user1);
        executor.safe(user2);
        executor.safe(user3);
        UserDataSet userX = executor.load(2, UserDataSet.class);

        System.out.println("Name = " + userX.getName() + ", Age = " + userX.getAge());

    }
}
