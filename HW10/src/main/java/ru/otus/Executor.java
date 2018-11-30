package ru.otus;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

public class Executor {

    private Connection connection;
    private DataSetQueryBuilder queryBuilder = new DataSetQueryBuilder();

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void save(DataSet dataSet) throws SQLException, IllegalAccessException {
        try (PreparedStatement statement = connection.prepareStatement(queryBuilder.getInsertQuery(dataSet))) {
            List<Field> fields = ReflectionHelper.getClassFields(dataSet);
            for (int i = 1; i <= fields.size(); i++) {
                Field localField = fields.get(i - 1);
                localField.setAccessible(true);
                if (ReflectionHelper.isInt(localField)) {
                    statement.setInt(i, (Integer) localField.get(dataSet));
                } else if (ReflectionHelper.isLong(localField)) {
                    statement.setLong(i, (Long) localField.get(dataSet));
                } else {
                    statement.setString(i, String.valueOf(localField.get(dataSet)));
                }
            }
            statement.execute();
        }
    }


    public DataSet load(Class<? extends DataSet> clazz, int userId) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        try (PreparedStatement stmt = connection.prepareStatement(DbQueries.USER_SELECT)) {
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            DataSet dataSet = clazz.getConstructor().newInstance();
            int columnCount = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSet.getMetaData().getColumnName(i);
                Object value = resultSet.getObject(columnName);
                ReflectionHelper.setField(dataSet, columnName, value);
            }
            return dataSet;
        }
    }

    public void execute(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }
}