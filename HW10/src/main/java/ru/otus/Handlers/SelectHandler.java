package ru.otus.Handlers;

import ru.otus.DataSet;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface SelectHandler {
    DataSet getResultQuery(ResultSet resultSet) throws SQLException;
}
