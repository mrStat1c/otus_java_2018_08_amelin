package ru.otus;

import ru.otus.Handlers.ExecuteHandler;
import ru.otus.Handlers.SelectHandler;

import java.sql.*;

public class Executor {

    private Connection connection;
    private Statement statement;
    private String sqlQuery;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(ExecuteHandler handler) throws SQLException, IllegalAccessException {
        try (Statement stmt = connection.createStatement()){
          stmt.execute(handler.getQuery());
        }
    }

    public DataSet execSelect(String select, SelectHandler handler) throws SQLException {
        try (Statement stmt = connection.createStatement()){
            ResultSet resultSet = stmt.executeQuery(select);
            return handler.getResultQuery(resultSet);
        }
    }
}
