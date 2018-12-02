package ru.otus;

import org.hibernate.cfg.Configuration;
import ru.otus.DataSets.AddressDataSet;
import ru.otus.DataSets.PhoneDataSet;
import ru.otus.DataSets.UserDataSet;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        Configuration configuration = DefaultConfigurationHolder.getConfiguration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

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

        UserDataSet userDataSet = dbService.load(UserDataSet.class, 1);
        System.out.println("Name = " + userDataSet.getName());
        System.out.println("Age = " + userDataSet.getAge());
        System.out.println("Phones:");
        userDataSet.getPhones().forEach(phone -> System.out.println(" " + phone.getNumber()));
        System.out.println("Address = " + userDataSet.getAddress().getStreet());

        dbService.shutdown();
    }
}
