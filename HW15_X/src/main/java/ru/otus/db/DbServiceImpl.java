package ru.otus.db;

import ru.otus.client.DBAddressee;
import ru.otus.client.MessageSystemContext;
import ru.otus.db.hibernateStorage.DbServiceHibernateImpl;
import ru.otus.db.storage.dataSets.UserDataSet;
import ru.otus.messageSystem.Address;
import ru.otus.messageSystem.MessageSystem;

public class DbServiceImpl implements DBAddressee {
    private final Address address;
    private final MessageSystemContext context;
    private final DbServiceHibernateImpl dbService;

    public DbServiceImpl(String address, MessageSystemContext context, DbServiceHibernateImpl dbService) {
        this.address = new Address(address);
        this.context = context;
        this.dbService = dbService;
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
        context.setDbAddress(this.address);
    }

    @Override
    public UserDataSet getUserById(long id) {
        return dbService.read(id);
    }

    @Override
    public UserDataSet addUser(UserDataSet user) {
        dbService.save(user);
        return user;
    }

    @Override
    public UserDataSet update(UserDataSet user) {
        dbService.save(user);
        return user;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }

}
