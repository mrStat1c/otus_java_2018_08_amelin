package ru.otus.DbService;

import org.hibernate.cfg.Configuration;
import ru.otus.DataSets.AddressDataSet;
import ru.otus.DataSets.PhoneDataSet;
import ru.otus.DataSets.UserDataSet;

public class DefaultConfiguration extends Configuration {

    public DefaultConfiguration() {
        this.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        this.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        this.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hw10" +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        this.setProperty("hibernate.connection.username", "root");
        this.setProperty("hibernate.connection.password", "root");
        this.setProperty("hibernate.show_sql", "false");
        this.setProperty("hibernate.hbm2ddl.auto", "update");
        this.setProperty("hibernate.connection.useSSL", "false");
        this.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        this.addAnnotatedClass(UserDataSet.class);
        this.addAnnotatedClass(PhoneDataSet.class);
        this.addAnnotatedClass(AddressDataSet.class);
    }
}
