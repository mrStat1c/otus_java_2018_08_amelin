package ru.otus.client;

import ru.otus.db.storage.dataSets.UserDataSet;
import ru.otus.messageSystem.Addressee;


public interface DBAddressee extends Addressee {
    void init();

    UserDataSet getUserById(long id);
    UserDataSet addUser(UserDataSet user);
    UserDataSet update(UserDataSet user);
}
