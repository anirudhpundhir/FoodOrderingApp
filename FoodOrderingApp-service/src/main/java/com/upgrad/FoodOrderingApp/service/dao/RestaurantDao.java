package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestaurantDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RestaurantEntity> getAllRestaurants() {
        try {
            return entityManager.createNamedQuery("allRestaurants", RestaurantEntity.class).getResultList();
        } catch(NoResultException nre) {
            return null;
        }
    }

    public List<RestaurantEntity> getRestaurantByName(String restaurantName){
        try {
            return entityManager.createNamedQuery("getRestaurantByName",
                    RestaurantEntity.class).setParameter(1,restaurantName).getResultList();
        } catch(NoResultException nre) {
            return null;
        }
    }

    public List<RestaurantEntity> getRestaurantByCategoryId(String categoryId){
        try {
            return entityManager.createNamedQuery("getRestaurantByCategoryId",
                    RestaurantEntity.class).setParameter(1,categoryId).getResultList();
        } catch(NoResultException nre) {
            return null;
        }
    }

    public RestaurantEntity getRestaurantById(String restaurantId){
        try {
            return entityManager.createNamedQuery("getRestaurantById",
                    RestaurantEntity.class).setParameter(1,restaurantId).getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }

    public RestaurantEntity getRestaurantByUUID(String uuid) {
        try {
            return entityManager.createNamedQuery("restaurantByUUID", RestaurantEntity.class).setParameter("uuid", uuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    public List<RestaurantEntity> restaurantsByRating() {
        try {
            return entityManager.createNamedQuery("allRestaurantsByRating", RestaurantEntity.class).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public RestaurantEntity updateRestaurantEntity(RestaurantEntity restaurantEntity) {
        return entityManager.merge(restaurantEntity);
    }

}