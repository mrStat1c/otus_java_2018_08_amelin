package ru.otus.Handlers;

import ru.otus.UserDataSet;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSelectExecuteHandler implements SelectHandler{

    @Override
    public UserDataSet getResultQuery(ResultSet resultSet) throws SQLException {
        resultSet.next();
        return new UserDataSet(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("age")
        );
    }
}
