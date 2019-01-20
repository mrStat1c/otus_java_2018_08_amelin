package ru.otus.DataSets;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private AddressDataSet address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<PhoneDataSet> phones;

    public UserDataSet(){

    }

    public UserDataSet(String name, int age, List<PhoneDataSet> phones, AddressDataSet address) {
        this.setId(-1);
        this.name = name;
        this.age = age;
        this.phones = phones;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public List<PhoneDataSet> getPhones(){return phones;}

    public void setPhones(List<PhoneDataSet> phones){this.phones = phones;}

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }
}
