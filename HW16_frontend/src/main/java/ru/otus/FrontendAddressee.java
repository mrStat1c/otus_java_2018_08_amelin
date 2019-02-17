package ru.otus;

import ru.otus.storage.dataSets.UserDataSet;

public interface FrontendAddressee extends Addressee {
    void init();

    void completeRequest(long id, String response);

    void completeRequest(long id, UserDataSet dataSet);

    void handleException(long id, String exceptionInfo);
}

