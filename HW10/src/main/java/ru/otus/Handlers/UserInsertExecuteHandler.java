package ru.otus.Handlers;

import ru.otus.UserDataSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInsertExecuteHandler implements ExecuteHandler {

    private String name;
    private int age;

    public UserInsertExecuteHandler(UserDataSet user){
        this.name = user.getName();
        this.age = user.getAge();
    }


    public void accept(PreparedStatement statement) throws SQLException {
        statement.setString(1, name);
        statement.setInt(2, age);
        statement.execute();
    }

}
