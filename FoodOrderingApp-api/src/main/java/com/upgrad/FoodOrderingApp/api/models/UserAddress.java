package org.upgrad.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_address")
public class UserAddress {



    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="type")
    private String type;



    @Column(name="user_id")

    private int user_id;

    @Column(name="address_id")
    private int address_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public UserAddress(){}

    public UserAddress(String type, int user_id, int address_id) {
        //this.id = id;
        if(!type.equalsIgnoreCase("perm")){
            type="temp";
        }

        this.type = type;
        this.user_id = user_id;
        this.address_id = address_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddress that = (UserAddress) o;
        return id == that.id &&
                user_id == that.user_id &&
                address_id == that.address_id &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, user_id, address_id);
    }
    @Override
    public String toString() {
        return "UserAddress[" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", user_id=" + user_id +
                ", address_id=" + address_id +
                '[';
    }

}
