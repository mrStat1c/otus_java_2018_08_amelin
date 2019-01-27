package ru.otus.client.messages;

import ru.otus.client.DBAddressee;
import ru.otus.client.MsgToDB;
import ru.otus.db.storage.dataSets.UserDataSet;
import ru.otus.messageSystem.Address;

public class MsgUpdateUser extends MsgToDB {
    private final UserDataSet userDataSet;

    public MsgUpdateUser(Address from, Address to, long requestId, UserDataSet userDataSet) {
        super(from, to, requestId);
        this.userDataSet = userDataSet;
    }

    @Override
    public void exec(DBAddressee dbAddressee) {
        try {
            UserDataSet userAnswer = dbAddressee.update(userDataSet);
            dbAddressee.getMS().sendMessage(new MsgUserToFrontend(getTo(), getFrom(), getId(), userAnswer));
        } catch (Exception e) {
            e.printStackTrace();
            dbAddressee.getMS().sendMessage(new MsgError(getTo(), getFrom(), getId(), "Ошибка при сохранении пользователя"));
        }
    }
}
