package ru.otus;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Executor {

    private Driver driver;
    private Connection connection;
    private Statement statement;
    private String sqlQuery;

    public Executor() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
        connection = DriverManager.getConnection(
                String.format("jdbc:mysql://%s?user=%s&password=%s&%s", "localhost:3306", "root", "root",
                        "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"));
        statement = connection.createStatement();
    }

    public <T extends DataSet> void safe (T user) throws SQLException {
        if (user instanceof UserDataSet) {
            UserDataSet userDataSet = (UserDataSet) user;
            sqlQuery = "INSERT INTO hw10.user (name, age) VALUES ('" + userDataSet.getName() + "'," + userDataSet.getAge() + ");";
        } else {
            sqlQuery = "INSERT INTO hw10.user VALUES ();";
        }
        statement.executeUpdate(sqlQuery);
    }

    public <T extends DataSet> T load (long id, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
        sqlQuery = "SELECT * FROM hw10.user WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        resultSet.next();
        if (clazz.isAssignableFrom(UserDataSet.class)) {
            return (T) new UserDataSet(resultSet.getString("name"), resultSet.getInt("age"));
        } else {
           throw new RuntimeException("Invalid class " + clazz);
        }

    }
}
