package ru.otus.DAO;

import org.hibernate.Session;
import ru.otus.DataSets.PhoneDataSet;

public class PhoneDataSetDAO {

    private Session session;

    PhoneDataSetDAO(Session session){
        this.session = session;
    }

    public void save(PhoneDataSet dataSet) {
        session.save(dataSet);
    }

    public PhoneDataSet read(long id) {
        return session.load(PhoneDataSet.class, id);
    }
}
