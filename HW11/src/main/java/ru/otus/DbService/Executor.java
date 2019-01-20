package ru.otus.DbService;

import ru.otus.DataSetQueryBuilder;
import ru.otus.DataSets.DataSet;
import ru.otus.DbQueries;
import ru.otus.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Executor implements DbService{

    private Connection connection;
    private DataSetQueryBuilder queryBuilder = new DataSetQueryBuilder();

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void save(DataSet dataSet) throws SQLException, IllegalAccessException, NoSuchFieldException {
        if (dataSet != null) {
            List<String> tableColumnList = getTableColumnList("user");
            try (PreparedStatement statement = connection.prepareStatement(queryBuilder.getInsertQuery(
                    dataSet, tableColumnList))) {
                for (int i = 0; i < tableColumnList.size(); i++) {
                    Field localField = dataSet.getClass().getDeclaredField(tableColumnList.get(i));
                    localField.setAccessible(true);
                    statement.setObject(i + 1, localField.get(dataSet));
                }
                statement.execute();
            }
        } else {
            System.err.println("Object was not saved as null");
        }
    }


    public DataSet load(Class<? extends DataSet> clazz, int userId) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        if (clazz != null) {
            try (PreparedStatement stmt = connection.prepareStatement(DbQueries.USER_SELECT)) {
                stmt.setInt(1, userId);
                ResultSet resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    DataSet dataSet = clazz.getConstructor().newInstance();
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = resultSet.getMetaData().getColumnName(i);
                        Object value = resultSet.getObject(columnName);
                        ReflectionHelper.setField(dataSet, columnName, value);
                    }
                    return dataSet;
                } else {
                    System.err.println("Entity with id = " + userId + " not found in Db!");
                    return null;
                }
            }
        } else {
            System.err.println("Input parameter 'clazz' is null!");
            return null;
        }
    }


    public void execute(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public List<String> getTableColumnList(String tableName) throws SQLException {
        String query = "SHOW COLUMNS FROM " + tableName;
        List<String> tableColumns = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String columnName = resultSet.getString("field");
                if (!columnName.equals("id")) {
                    tableColumns.add(columnName);
                }
            }
        }
        return tableColumns;
    }

    @Override
    public void shutdown() throws SQLException {
       connection.close();
    }

}