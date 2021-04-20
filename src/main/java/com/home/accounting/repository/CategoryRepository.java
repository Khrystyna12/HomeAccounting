package com.home.accounting.repository;

import com.home.accounting.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select category.id, of_expense, title, user_entity_id " +
            "from category inner join user_entity on user_entity_id = user_entity.id where of_expense = true " +
            "and email = ?1", nativeQuery = true)
    List<Category> findAllOfExpenseByUserEmail(String email);

    @Query(value = "select category.id, of_expense, title, user_entity_id " +
            "from category inner join user_entity on user_entity_id = user_entity.id where of_expense = false " +
            "and email = ?1", nativeQuery = true)
    List<Category> findAllOfIncomeByUserEmail(String email);
}
