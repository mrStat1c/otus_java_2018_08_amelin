package ru.otus;

public class DbQueries {

    public static final String USER_SELECT = "SELECT * FROM user WHERE id = ?";
    public static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS user (" +
            "  id bigint(20) NOT NULL AUTO_INCREMENT," +
            "  name varchar(255) DEFAULT NULL," +
            "  age int(11) DEFAULT NULL," +
            "  PRIMARY KEY (id));";
}
