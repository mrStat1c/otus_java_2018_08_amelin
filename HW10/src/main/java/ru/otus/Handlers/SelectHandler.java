package ru.otus.Handlers;

import ru.otus.DataSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface SelectHandler {
    DataSet getResultQuery(PreparedStatement preparedStatement) throws SQLException;
}
