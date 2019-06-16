package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CategoryDao;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryBusinessService {

    @Autowired
    CategoryDao categoryDao;

    @Transactional
    public List<CategoryEntity> getCategories() {
        return categoryDao.getCategories();
    }

    @Transactional
    public List<CategoryEntity> getCategoriesById(String categoryId) {
        return categoryDao.getCategoriesById(categoryId);
    }


}
