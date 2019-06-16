package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.RestaurantDetailsResponse;
import com.upgrad.FoodOrderingApp.api.model.RestaurantDetailsResponseAddress;
import com.upgrad.FoodOrderingApp.api.model.RestaurantDetailsResponseAddressState;
import com.upgrad.FoodOrderingApp.service.businness.AddressBusinessService;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantBusinessService;
import com.upgrad.FoodOrderingApp.service.businness.StateBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class RestaurantController {

    @Autowired
    private RestaurantBusinessService restaurantBusinessService;

    @Autowired
    private AddressBusinessService addressBusinessService;

    @Autowired
    private StateBusinessService stateBusinessService;

    @RequestMapping(method = RequestMethod.GET, path = "/restaurant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getAllRestaurants() {

        //getting the list of all questions using the bearertoken as parameter
        final List<RestaurantEntity> allRestaurants = restaurantBusinessService.getAllRestaurants();

        //adding the list of questions to the question detail response
        List<RestaurantDetailsResponse> details = new ArrayList<>();
        for (RestaurantEntity n: allRestaurants) {
            RestaurantDetailsResponse detail = new RestaurantDetailsResponse();

            detail.setId(n.getUuid());
            detail.setRestaurantName(n.getRestaurantName());
            detail.setPhotoURL(n.getPhotoUrl());
            detail.setCustomerRating(n.getCustomerRating());
            detail.setAveragePrice(n.getAvgPriceForTwo());
            detail.setNumberCustomersRated(n.getNumCustomersRated());

            AddressEntity addressEntity = addressBusinessService.getAddressById(n.getAddress().getId());
            RestaurantDetailsResponseAddress responseAddress = new RestaurantDetailsResponseAddress();

            responseAddress.setId(UUID.fromString(addressEntity.getUuid()));
            responseAddress.setFlatBuildingName(addressEntity.getFlatBldgNumber());
            responseAddress.setLocality(addressEntity.getLocality());
            responseAddress.setCity(addressEntity.getCity());
            responseAddress.setPincode(addressEntity.getPincode());

            StateEntity stateEntity = stateBusinessService.getStateById(addressEntity.getState().getId());
            RestaurantDetailsResponseAddressState responseAddressState = new RestaurantDetailsResponseAddressState();

            responseAddressState.setId(UUID.fromString(stateEntity.getUuid()));
            responseAddressState.setStateName(stateEntity.getStateName());
            responseAddress.setState(responseAddressState);

            detail.setAddress(responseAddress);

            details.add(detail);
        }

        if(details.size()==0){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/restaurant/name/{reastaurant_name}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantEntity>> getRestaurantByName(@PathVariable("reastaurant_name")
                                                                            String restaurantName) throws RestaurantNotFoundException {

        if(StringUtils.isEmpty(restaurantName)){
            throw new RestaurantNotFoundException("RNF-003","Restaurant name field should not be empty");
        }
        List<RestaurantEntity> restaurantEntity=restaurantBusinessService.getRestaurantByName(restaurantName);

         if(restaurantEntity==null){
             return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
         }
        return new ResponseEntity<>(restaurantEntity,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/restaurant/category/{category_id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<RestaurantEntity>> getRestaurantByCategoryId(@PathVariable("category_id")
                                                                        String categoryId) throws CategoryNotFoundException {
        if(StringUtils.isEmpty(categoryId)){
            throw new CategoryNotFoundException("CNF-001","Category id field should not be empty");
        }
        List<RestaurantEntity> restaurantEntity=restaurantBusinessService.getRestaurantByCategoryId(categoryId);

        if(restaurantEntity==null){
            throw new CategoryNotFoundException("CNF-002","No category by this id");
        }
        return new ResponseEntity<>(restaurantEntity,HttpStatus.OK);
    }


}