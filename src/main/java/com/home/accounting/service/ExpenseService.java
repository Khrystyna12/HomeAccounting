package com.home.accounting.service;

import com.home.accounting.model.Expense;

import java.util.List;

public interface ExpenseService {
    Expense create(Expense expense);
    Expense readById(long id);
    List<Expense> getByUserEmail(String email);
}
