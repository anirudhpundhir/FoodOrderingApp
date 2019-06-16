package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.service.businness.CategoryBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.exception.CategoryNotFoundException;
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

@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    CategoryBusinessService categoryBusinessService;

    @RequestMapping(method = RequestMethod.GET, path = "/category",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CategoryEntity>> getAllCategory() {

        List<CategoryEntity> categories=categoryBusinessService.getCategories();

        if(categories==null){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/category/{category_id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CategoryEntity>> getAllCategoryById(@PathVariable("category_id") String categoryId) throws CategoryNotFoundException {


        if(StringUtils.isEmpty(categoryId)){
            throw new CategoryNotFoundException("CNF-001","Category id field should not be empty");
        }
        List<CategoryEntity> categories=categoryBusinessService.getCategoriesById(categoryId);

        if(categories==null){
            throw new CategoryNotFoundException("CNF-002","No category by this id");
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }


}
