package ru.otus;


import ru.otus.storage.dataSets.UserDataSet;

public interface DBAddressee extends Addressee {
    void init();

    UserDataSet getUserById(long id);
    UserDataSet addUser(UserDataSet user);
    UserDataSet update(UserDataSet user);
}
