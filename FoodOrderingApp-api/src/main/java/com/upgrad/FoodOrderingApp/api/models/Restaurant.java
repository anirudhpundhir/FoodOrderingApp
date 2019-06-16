package org.upgrad.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "restaurant_name")
    @NotNull
    private String restaurantName;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "user_rating")
    @NotNull
    private double userRating;

    @Column(name = "average_price_for_two")
    @NotNull
    private int avgPrice;

    @Column(name = "number_of_users_rated")
    @NotNull
    private int numberUsersRated;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    /*
     * OneToMany - Fetch categories objects automatically and o need to call getter
     * JsonManagedReference - used for JSON Mapping and also avoids infinite recursion error
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="restaurant", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<RestaurantCategory> restaurantCategories;


    public Restaurant() {
    }

    public Restaurant(@NotNull String restaurantName, String photoUrl, @NotNull double userRating, @NotNull int avgPrice, @NotNull int numberUsersRated, Address address) {
        this.restaurantName = restaurantName;
        this.photoUrl = photoUrl;
        this.userRating = userRating;
        this.avgPrice = avgPrice;
        this.numberUsersRated = numberUsersRated;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getNumberUsersRated() {
        return numberUsersRated;
    }

    public void setNumberUsersRated(int numberUsersRated) {
        this.numberUsersRated = numberUsersRated;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<RestaurantCategory> getRestaurantCategories() {
        return restaurantCategories;
    }

    public void setRestaurantCategories(List<RestaurantCategory> restaurantCategories) {
        this.restaurantCategories = restaurantCategories;
    }

}

