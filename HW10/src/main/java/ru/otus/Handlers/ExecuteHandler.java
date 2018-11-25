package ru.otus.Handlers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface ExecuteHandler {
    String getQuery() throws SQLException, IllegalAccessException;
}
