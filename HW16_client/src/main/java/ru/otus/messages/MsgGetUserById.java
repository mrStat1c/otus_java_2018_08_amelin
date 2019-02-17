package ru.otus.messages;


import ru.otus.Address;
import ru.otus.DBAddressee;
import ru.otus.storage.dataSets.UserDataSet;

public class MsgGetUserById extends MsgToDB {
    private final long userId;

    public MsgGetUserById(Address from, Address to, long requestId, long userId) {
        super(from, to, requestId);
        this.userId = userId;
    }

    @Override
    public void exec(DBAddressee dbAddressee) {
        try {
            UserDataSet userAnswer = dbAddressee.getUserById(userId);
            dbAddressee.getMS().sendMessage(new MsgUserToFrontend(getTo(), getFrom(), getId(), userAnswer));
        } catch (Exception e) {
            e.printStackTrace();
            dbAddressee.getMS().sendMessage(new MsgError(getTo(), getFrom(), getId(), "Ошибка при получении пользователя"));
        }
    }
}
