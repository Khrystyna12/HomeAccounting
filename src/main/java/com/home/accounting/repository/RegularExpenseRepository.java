package com.home.accounting.repository;

import com.home.accounting.model.RegularExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularExpenseRepository extends JpaRepository<RegularExpense, Long> {
}
