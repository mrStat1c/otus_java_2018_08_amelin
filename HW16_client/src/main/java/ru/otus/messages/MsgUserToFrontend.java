package ru.otus.messages;

import ru.otus.Address;
import ru.otus.FrontendAddressee;
import ru.otus.storage.dataSets.UserDataSet;

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
