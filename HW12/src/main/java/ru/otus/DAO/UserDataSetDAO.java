package ru.otus.DAO;

import org.hibernate.Session;
import ru.otus.DataSets.UserDataSet;

public class UserDataSetDAO {

    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public void save(UserDataSet dataSet) {
        session.save(dataSet);
    }

    public UserDataSet load(long id) {
        return session.load(UserDataSet.class, id);
    }
}
