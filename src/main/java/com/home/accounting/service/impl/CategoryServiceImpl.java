package com.home.accounting.service.impl;

import com.home.accounting.model.Category;
import com.home.accounting.repository.CategoryRepository;
import com.home.accounting.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Category cannot be 'null'");
        }
    }

    @Override
    public Category readById(long id) {
        Optional<Category> optional = categoryRepository.findById(id);
            return optional.get();
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.isEmpty() ? new ArrayList<>() : categories;
    }
}
