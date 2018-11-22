package ru.otus.Handlers;

import ru.otus.DataSet;
import ru.otus.UserDataSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSelectExecuteHandler implements SelectHandler{

    private int userId;

    public UserSelectExecuteHandler(int userId){
     this.userId = userId;
    }

    @Override
    public UserDataSet getResultQuery(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, userId);
        ResultSet resultSet =  preparedStatement.executeQuery();
        resultSet.next();
        return new UserDataSet(resultSet.getString("name"), resultSet.getInt("age"));
    }
}
