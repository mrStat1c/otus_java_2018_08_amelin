package ru.otus.client.messages;

import ru.otus.client.FrontendAddressee;
import ru.otus.client.MsgToFrontend;
import ru.otus.db.storage.dataSets.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgUserToFrontend extends MsgToFrontend {
    private final UserDataSet userDataSet;

    public MsgUserToFrontend(Address from, Address to, long requestId, UserDataSet userDataSet) {
        super(from, to, requestId);
        this.userDataSet = userDataSet;
    }

    @Override
    public void exec(FrontendAddressee frontendAddressee) {
        frontendAddressee.completeRequest(getId(), userDataSet);
    }
}
