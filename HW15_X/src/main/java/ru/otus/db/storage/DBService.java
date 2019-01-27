package ru.otus.db.storage;

import ru.otus.db.storage.dataSets.UserDataSet;

import java.util.List;

public interface DBService extends AutoCloseable {
    void save(UserDataSet dataSet);
    UserDataSet read(long id);
    UserDataSet readByName(String name);
    List<UserDataSet> readAll();
}
