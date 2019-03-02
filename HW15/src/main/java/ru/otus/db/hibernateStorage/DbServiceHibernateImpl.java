package ru.otus.db.hibernateStorage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.db.hibernateStorage.dao.UserDAO;
import ru.otus.db.storage.DBService;
import ru.otus.db.storage.dataSets.AddressDataSet;
import ru.otus.db.storage.dataSets.PhoneDataSet;
import ru.otus.db.storage.dataSets.UserDataSet;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DbServiceHibernateImpl implements DBService {
    private final SessionFactory sessionFactory;

    public DbServiceHibernateImpl() {
        
        Configuration configuration = new Configuration();


        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hw10" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);


        sessionFactory = createSessionFactory(configuration);
    }

    public DbServiceHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void save(UserDataSet dataSet) {
        runInSession(session -> {
            UserDAO dao = new UserDAO(session);
            dao.save(dataSet);
        });
    }

    @Override
    public UserDataSet read(long id) {
        return runInSession(session -> {
            UserDAO dao = new UserDAO(session);
            return dao.read(id);
        });
    }

    @Override
    public UserDataSet readByName(String name) {
        throw new UnsupportedOperationException("...hibernateStorage.DbDerviceImpl.readByName(String name)");
    }

    @Override
    public List<UserDataSet> readAll() {
        throw new UnsupportedOperationException("...hibernateStorage.DbDerviceImpl.readAll()");
    }

    @Override
    public void close() {
        sessionFactory.close();
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    private void runInSession(Consumer<Session> executor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            executor.accept(session);
            transaction.commit();
        }
    }

}
