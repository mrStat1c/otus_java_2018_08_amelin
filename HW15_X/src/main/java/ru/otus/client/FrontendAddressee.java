package ru.otus.client;

import ru.otus.db.storage.dataSets.UserDataSet;
import ru.otus.messageSystem.Addressee;

public interface FrontendAddressee extends Addressee {
    void init();

    void completeRequest(long id, String response);

    void completeRequest(long id, UserDataSet dataSet);

    void handleException(long id, String exceptionInfo);
}

