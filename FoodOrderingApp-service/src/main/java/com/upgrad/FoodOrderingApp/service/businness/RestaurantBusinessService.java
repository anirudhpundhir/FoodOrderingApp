package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.RestaurantDao;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantBusinessService {

    @Autowired
    private RestaurantDao restaurantDao;

    //A Method which takes the accessToken as parameter for authorization for getAllQuestions endpoint///////
    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantDao.getAllRestaurants();
    }

    public List<RestaurantEntity> getRestaurantByName(String restaurantName){
        return restaurantDao.getRestaurantByName(restaurantName);
    }

    public List<RestaurantEntity> getRestaurantByCategoryId(String categoryId){
        return restaurantDao.getRestaurantByCategoryId(categoryId);
    }
}