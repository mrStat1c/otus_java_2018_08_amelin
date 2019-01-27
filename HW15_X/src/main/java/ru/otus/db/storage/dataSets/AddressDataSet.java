package ru.otus.db.storage.dataSets;

import ru.otus.db.storage.DataSet;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class AddressDataSet extends DataSet {
    @OneToOne
    private UserDataSet user;

    private String street;

    public AddressDataSet() {
    }

    public AddressDataSet(UserDataSet user, String street) {
        this.user = user;
        this.street = street;
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ru.otus.db.storage.dataSets.AddressDataSet that = (ru.otus.db.storage.dataSets.AddressDataSet) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getStreet(), that.getStreet());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUser(), getStreet());
    }
}
