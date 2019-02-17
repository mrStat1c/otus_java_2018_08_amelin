package ru.otus.messages;


import ru.otus.Address;
import ru.otus.FrontendAddressee;

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
