package com.upgrad.FoodOrderingApp.service.entity;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant")
@NamedQueries(
        {
                @NamedQuery(name="getRestaurantById",query = "select r from RestaurantEntity r " +
                        "where r.uuid=:uuid"),
                @NamedQuery(name = "allRestaurants", query = "select r from " +
                        "RestaurantEntity r order by r.customerRating desc"),
                @NamedQuery(name="getRestaurantByName",query = "select r from RestaurantEntity r " +
                        "where r.restaurantName=:restaurantName"),
                @NamedQuery(name = "allRestaurantBycategory" , query = "select r from RestaurantEntity r  " +
                        "INNER JOIN RestaurantCategoryEntity rc on r.id = rc.restaurant " +
                        "INNER JOIN CategoryEntity c on rc.category = c.id Where c.uuid =:uuid")
        }
)

public class RestaurantEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "UUID")
    @NotNull
    @Size(max = 200)
    private UUID uuid;

    @Column(name = "RESTAURANT_NAME")
    @NotNull
    @Size(max = 50)
    private String restaurantName;

    @Column(name = "PHOTO_URL")
    @NotNull
    @Size(max = 255)
    private String photoUrl;

    @Column(name = "CUSTOMER_RATING")
    @NotNull
    private BigDecimal customerRating;

    @Column(name = "NUMBER_OF_CUSTOMERS_RATED")
    @NotNull
    private Integer numCustomersRated;

    @Column(name = "AVERAGE_PRICE_FOR_TWO")
    @NotNull
    private Integer avgPriceForTwo;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;

    @ManyToMany
    @JoinTable(name = "restaurant_item", joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<ItemEntity> items = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurant_category", joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories = new ArrayList<>();


    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public BigDecimal getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(BigDecimal customerRating) {
        this.customerRating = customerRating;
    }

    public Integer getNumCustomersRated() {
        return numCustomersRated;
    }

    public void setNumCustomersRated(Integer numCustomersRated) {
        this.numCustomersRated = numCustomersRated;
    }

    public Integer getAvgPriceForTwo() {
        return avgPriceForTwo;
    }

    public void setAvgPriceForTwo(Integer avgPriceForTwo) {
        this.avgPriceForTwo = avgPriceForTwo;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}