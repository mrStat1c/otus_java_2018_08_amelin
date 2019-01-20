package ru.otus.DbService;

import ru.otus.DataSets.DataSet;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbService {

    void save(DataSet dataSet) throws Exception;

    DataSet load(Class<? extends DataSet> clazz, int id) throws Exception;

    void shutdown() throws SQLException;
}
