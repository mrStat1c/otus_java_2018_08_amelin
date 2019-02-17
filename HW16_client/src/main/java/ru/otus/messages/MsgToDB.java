package ru.otus.messages;


import ru.otus.Address;
import ru.otus.Addressee;
import ru.otus.DBAddressee;

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
