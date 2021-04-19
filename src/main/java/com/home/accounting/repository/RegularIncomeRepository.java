package com.home.accounting.repository;

import com.home.accounting.model.RegularIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularIncomeRepository extends JpaRepository<RegularIncome, Long> {
}
