package com.home.accounting.service;

import com.home.accounting.model.Income;

import java.util.List;

public interface IncomeService {
    Income create(Income income);
    Income readById(long id);
    List<Income> getByUserEmail(String email);
}
