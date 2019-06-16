package org.upgrad.models;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "type", nullable = false)
    private String type;

    public Item() {
    }

    public Item(String itemName, double price, String type) {
        this.itemName = itemName;
        this.price = price;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
