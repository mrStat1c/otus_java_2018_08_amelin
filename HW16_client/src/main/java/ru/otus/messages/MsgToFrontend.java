package ru.otus.messages;


import ru.otus.Address;
import ru.otus.Addressee;
import ru.otus.FrontendAddressee;

public abstract class MsgToFrontend extends IdentifiedMessage {
    public MsgToFrontend(Address from, Address to, long id) {
        super(from, to, id);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendAddressee) {
            exec((FrontendAddressee) addressee);
        } else {
            //todo error!
        }
    }

    public abstract void exec(FrontendAddressee frontendAddressee);
}