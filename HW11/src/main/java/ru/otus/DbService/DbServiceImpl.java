package ru.otus.DbService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.DAO.UserDataSetDAO;
import ru.otus.DataSets.DataSet;
import ru.otus.DataSets.UserDataSet;

import java.util.function.Function;

public class DbServiceImpl implements DbService {

    private final SessionFactory sessionFactory;

    public DbServiceImpl(Configuration configuration) {
        this.sessionFactory =  createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void save(DataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save((UserDataSet) dataSet);
            transaction.commit();
        }
    }

    @Override
    public DataSet load(Class<? extends DataSet> clazz, int id) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.load(id);
        });
    }


    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
