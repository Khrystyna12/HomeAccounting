package com.home.accounting.repository;

import com.home.accounting.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    @Query(value = "select income.id, income.amount, comment, date, account_id, category_id, subcategory_id " +
            "from income inner join account on account_id = account.id " +
            "inner join user_entity on account.user_entity_id = user_entity.id " +
            "where email = ?1", nativeQuery = true)
    List<Income> findAllByUserEmail(String email);
}
