package com.home.accounting.service;

import com.home.accounting.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    Category readById(long id);
    List<Category> getCategoriesOfExpenseByUserEmail(String email);
    List<Category> getCategoriesOfIncomeByUserEmail(String email);
    List<Category> getAll();
}
