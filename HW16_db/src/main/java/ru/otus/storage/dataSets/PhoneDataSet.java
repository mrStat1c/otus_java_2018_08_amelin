package ru.otus.storage.dataSets;

import ru.otus.storage.DataSet;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PhoneDataSet extends DataSet {
    @ManyToOne
    private UserDataSet user;
    private String number;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
