package otus.DataSets;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet {


    @Column(name = "street")
    private String street;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserDataSet user;

    public AddressDataSet() {
    }

    public AddressDataSet(String street, UserDataSet user){
        this.street = street;
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }

    public UserDataSet getUser() {
        return user;
    }

}
