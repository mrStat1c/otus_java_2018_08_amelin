package otus.DAO;

import org.hibernate.Session;
import otus.DataSets.AddressDataSet;

public class AddressDataSetDAO {

    private Session session;

    public AddressDataSetDAO(Session session){
        this.session = session;
    }

    public void save(AddressDataSet dataSet) {
        session.save(dataSet);
    }

    public AddressDataSet read(long id) {
        return session.load(AddressDataSet.class, id);
    }
}
