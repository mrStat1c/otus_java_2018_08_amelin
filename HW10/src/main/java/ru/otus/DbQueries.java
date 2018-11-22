package ru.otus;

public class DbQueries {

    public static final String USER_INSERT = "INSERT INTO hw10.user (name, age) VALUES (?,?)";
    public static final String USER_SELECT = "SELECT * FROM hw10.user WHERE id = ?";
}
