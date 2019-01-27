package ru.otus.client;

import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.Addressee;

public abstract class MsgToDB extends IdentifiedMessage {
    public MsgToDB(Address from, Address to, long id) {
        super(from, to, id);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBAddressee) {
            exec((DBAddressee) addressee);
        }
    }

    public abstract void exec(DBAddressee dbAddressee);
}
