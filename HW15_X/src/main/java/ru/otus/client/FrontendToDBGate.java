package ru.otus.client;

import ru.otus.db.storage.dataSets.UserDataSet;

import java.util.function.Consumer;

public interface FrontendToDBGate {
    void getUserById(long id, Consumer<UserDataSet> onComplete, Consumer<String> onError);
}
