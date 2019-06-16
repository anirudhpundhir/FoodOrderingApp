package org.upgrad.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "states")
public class States {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "state_name")
    private String stateName;

    public States() {
    }

    public States(String stateName) {
        this.stateName = stateName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "States[" +
                "id=" + id +
                ", stateName='" + stateName + '\'' +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        States states = (States) o;
        return id == states.id &&
                Objects.equals(stateName, states.stateName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, stateName);}

}