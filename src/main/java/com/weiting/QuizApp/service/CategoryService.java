package com.weiting.QuizApp.service;

import com.weiting.QuizApp.dao.CategoryDAO;
import com.weiting.QuizApp.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoryDAO categoryDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    public String getCategoryNameById(Integer categoryId) {
        return categoryDAO.getCategoryNameById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
