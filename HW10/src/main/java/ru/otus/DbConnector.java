package ru.otus;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    public void registerDriver(String driverName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class.forName(driverName).getConstructor().newInstance();
    }

    public Connection getConnection(String url, String port, String scheme, String user, String password) throws SQLException {
        return DriverManager.getConnection(
        String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&%s", url, port, scheme, user, password,
                        "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"));

    }

}
