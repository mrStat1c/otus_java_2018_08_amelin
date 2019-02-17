package ru.otus.messages;


import ru.otus.Address;
import ru.otus.Message;

public abstract class IdentifiedMessage extends Message {
    private final long id;
    public IdentifiedMessage(Address from, Address to, long id) {
        super(from, to);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
