package ru.otus.client;


import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.Addressee;


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