package com.home.accounting.service.impl;

import com.home.accounting.model.Expense;
import com.home.accounting.repository.ExpenseRepository;
import com.home.accounting.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseRepository expenseRepository;

    @Override
    public Expense create(Expense expense) {
        try {
            return expenseRepository.save(expense);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Expense cannot be 'null'");
        }
    }

    @Override
    public Expense readById(long id) {
        Optional<Expense> optional = expenseRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Expense with id " + id + " not found");
    }

    @Override
    public List<Expense> getByUserEmail(String email) {
        List<Expense> expenses = expenseRepository.findAllByUserEmail(email);
        return expenses.isEmpty() ? new ArrayList<>() : expenses;
    }
}
