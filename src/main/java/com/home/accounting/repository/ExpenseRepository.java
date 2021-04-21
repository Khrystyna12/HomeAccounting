package com.home.accounting.repository;

import com.home.accounting.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query(value = "select expense.id, expense.amount, comment, date, account_id, category_id, subcategory_id " +
            "from expense inner join account on account_id = account.id " +
            "inner join user_entity on account.user_entity_id = user_entity.id " +
            "where email = ?1", nativeQuery = true)
    List<Expense> findAllByUserEmail(String email);
}
