package ru.otus;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Executor {

    private Connection connection;
    private DataSetQueryBuilder queryBuilder = new DataSetQueryBuilder();

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void save(DataSet dataSet) throws SQLException, IllegalAccessException, NoSuchFieldException {
        try (Statement stmt = connection.createStatement()){
          stmt.execute(queryBuilder.getInsertQuery(dataSet));
        }
    }


    public DataSet load(Class<? extends DataSet> clazz, int userId) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        try (PreparedStatement stmt = connection.prepareStatement(DbQueries.USER_SELECT)){
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            DataSet dataSet = clazz.getConstructor().newInstance();
            int columnCount = resultSet.getMetaData().getColumnCount();
            for(int i = 1; i <= columnCount; i++){
                String columnName = resultSet.getMetaData().getColumnName(i);
                String columnValue = resultSet.getString(i);
                ReflectionHelper.setField(dataSet, columnName, columnValue);
            }
            return dataSet;
        }
    }

    public void execute(String query) throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.execute(query);
        }
    }
}