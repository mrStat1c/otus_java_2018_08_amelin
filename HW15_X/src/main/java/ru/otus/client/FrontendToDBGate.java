package ru.otus.client;

import ru.otus.db.storage.dataSets.UserDataSet;

import java.util.concurrent.Future;
import java.util.function.Consumer;

public interface FrontendToDBGate {
    void getUserById(long id, Consumer<UserDataSet> onComplete, Consumer<String> onError);
    void addUser(UserDataSet user, Consumer<UserDataSet> onComplete, Consumer<String> onError);
    void update(UserDataSet user, Consumer<UserDataSet> onComplete, Consumer<String> onError);

}
