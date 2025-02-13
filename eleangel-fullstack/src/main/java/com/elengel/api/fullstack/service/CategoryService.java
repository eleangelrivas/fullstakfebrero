package com.elengel.api.fullstack.service;

import com.elengel.api.fullstack.dto.SaveCategory;
import com.elengel.api.fullstack.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(SaveCategory saveCategory);

    Category updateOneById(Long categoryId, SaveCategory saveCategory);

    Category disableOneById(Long categoryId);

    List<Object[]> countProductsByCategory();


    List<Object[]> getTopSellingProducts();
    List<Object[]> getTopSpendingClients();
    List<Object[]> getMonthlyRevenue();

}
