package otus;

import org.hibernate.cfg.Configuration;
import otus.DataSets.AddressDataSet;
import otus.DataSets.PhoneDataSet;
import otus.DataSets.UserDataSet;
import otus.DbService.DbService;
import otus.DbService.DbServiceImpl;
import otus.DbService.Executor;

import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

       //---------------------------- MyORM ---------------------------------//

        DbConnector dbConnector = new DbConnector();
        dbConnector.registerDriver("com.mysql.cj.jdbc.Driver");
        Connection connection = dbConnector.getConnection("localhost", "3306", "hw10","root", "root");

        UserDataSet user1 = new UserDataSet();
        user1.setName("Petr");
        user1.setAge(25);

        Executor executor = new Executor(connection);
        executor.execute(DbQueries.CREATE_USER_TABLE);
        executor.save(user1);

        UserDataSet userX = (UserDataSet) executor.load(UserDataSet.class, 2);
        System.out.println("Name = " + userX.getName() + ", age = " + userX.getAge());

        //---------------------------- Hibernate ---------------------------------//

        Configuration configuration = DefaultConfigurationHolder.getConfiguration();

        DbService dbService = new DbServiceImpl(configuration);

        UserDataSet user = new UserDataSet();
        user.setName("Vasya");
        user.setAge(34);

        PhoneDataSet phone1 = new PhoneDataSet();
        phone1.setNumber("453-534");
        phone1.setUser(user);
        PhoneDataSet phone2 = new PhoneDataSet();
        phone2.setNumber("777-904");
        phone2.setUser(user);
        user.setPhones(List.of(phone1, phone2));

        AddressDataSet address = new AddressDataSet();
        address.setStreet("Leninskiy prospect");
        address.setUser(user);
        user.setAddress(address);

        dbService.save(user);

        UserDataSet userDataSet = (UserDataSet) dbService.load(UserDataSet.class, 1);
        System.out.println("Name = " + userDataSet.getName());
        System.out.println("Age = " + userDataSet.getAge());
        System.out.println("Phones:");
        userDataSet.getPhones().forEach(phone -> System.out.println(" " + phone.getNumber()));
        System.out.println("Address = " + userDataSet.getAddress().getStreet());

        dbService.shutdown();
    }
}
