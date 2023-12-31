package com.weiting.QuizApp.service;

import com.weiting.QuizApp.dao.CategoryDAO;
import com.weiting.QuizApp.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    private final CategoryDAO categoryDAO;

    public HomeService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public String getCategoryNameById(Integer categoryId) {
        return categoryDAO.getCategoryNameById(categoryId);
    }


}
