package com.home.accounting.service.impl;

import com.home.accounting.model.Income;
import com.home.accounting.repository.IncomeRepository;
import com.home.accounting.service.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private IncomeRepository incomeRepository;

    @Override
    public Income create(Income income) {
        try {
            return incomeRepository.save(income);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Income cannot be 'null'");
        }
    }

    @Override
    public Income readById(long id) {
        Optional<Income> optional = incomeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Income with id " + id + " not found");
    }

    @Override
    public List<Income> getByUserEmail(String email) {
        List<Income> incomes = incomeRepository.findAllByUserEmail(email);
        return incomes.isEmpty() ? new ArrayList<>() : incomes;
    }
}
