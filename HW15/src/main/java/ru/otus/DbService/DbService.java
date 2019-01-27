package ru.otus.DbService;

import ru.otus.DataSets.UserDataSet;

public interface DbService {

    void save(UserDataSet dataSet);

    UserDataSet load(Class clazz, int id);

    void shutdown();

    long getRecordCount(String entityName);
}
