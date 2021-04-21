package com.home.accounting.dto;

import com.home.accounting.model.Income;
import com.home.accounting.service.AccountService;
import com.home.accounting.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class IncomeTransformer {
    private AccountService accountService;
    private CategoryService categoryService;

    public IncomeDto convertToDto(Income income) {
        return new IncomeDto(
                income.getId(),
                income.getDate(),
                Double.toString(income.getAmount()),
                income.getComment(),
                Long.toString(income.getAccount().getId()),
                Long.toString(income.getCategory().getId())
        );
    }

    public Income convertToEntity(IncomeDto incomeDto) {
        Income income = new Income();
        income.setId(incomeDto.getId());
        income.setDate(incomeDto.getDate());
        income.setAmount(Double.parseDouble(incomeDto.getAmount()));
        income.setComment(incomeDto.getComment());
        income.setAccount(accountService.readById(Long.parseLong(incomeDto.getAccount())));
        income.setCategory(categoryService.readById(Long.parseLong(incomeDto.getCategory())));
        return income;
    }
}
