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

    public void execUpdate(String update, ExecuteHandler handler) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(update)){
            handler.accept(stmt);
        }
    }

    public DataSet execSelect(String select, SelectHandler handler) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(select)){
             return handler.getResultQuery(stmt);
        }
    }
}
