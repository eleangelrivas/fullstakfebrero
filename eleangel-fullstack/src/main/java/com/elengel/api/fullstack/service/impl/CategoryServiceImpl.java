package com.elengel.api.fullstack.service.impl;

import com.elengel.api.fullstack.dto.SaveCategory;
import com.elengel.api.fullstack.exception.ObjectNotFoundException;
import com.elengel.api.fullstack.persistence.entity.Category;
import com.elengel.api.fullstack.persistence.repository.CategoryRepository;
import com.elengel.api.fullstack.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findOneById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category createOne(SaveCategory saveCategory) {

        Category category = new Category();
        category.setName(saveCategory.getName());
        category.setStatus(Category.CategoryStatus.ENABLED);

        return categoryRepository.save(category);
    }

    @Override
    public Category updateOneById(Long categoryId, SaveCategory saveCategory) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));

        categoryFromDB.setName(saveCategory.getName());

        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public Category disableOneById(Long categoryId) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));

        categoryFromDB.setStatus(Category.CategoryStatus.DISABLED);

        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public List<Object[]> countProductsByCategory() {
        return categoryRepository.countProductsByCategory();
    }

    /*solo reportes*/
    @Override
    public List<Object[]> getTopSellingProducts() {
        return categoryRepository.findTopSellingProducts();
    }

    @Override
    public List<Object[]> getTopSpendingClients() {
        return categoryRepository.findTopSpendingClients();
    }

    @Override
    public List<Object[]> getMonthlyRevenue() {
        return categoryRepository.findMonthlyRevenue();
    }
}
