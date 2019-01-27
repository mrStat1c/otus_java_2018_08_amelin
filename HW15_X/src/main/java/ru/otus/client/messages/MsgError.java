package ru.otus.client.messages;


import ru.otus.client.FrontendAddressee;
import ru.otus.client.MsgToFrontend;
import ru.otus.messageSystem.Address;

public class MsgError extends MsgToFrontend {
    private final String errorMessage;

    public MsgError(Address from, Address to, long id, String errorMessage) {
        super(from, to, id);
        this.errorMessage = errorMessage;
    }

    @Override
    public void exec(FrontendAddressee frontendAddressee) {
        frontendAddressee.handleException(this.getId(), errorMessage);
    }
}
