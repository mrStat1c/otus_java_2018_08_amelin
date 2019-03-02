package ru.otus.client;

import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.Message;

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
