package com.home.accounting.dto;

import com.home.accounting.model.Expense;
import com.home.accounting.service.AccountService;
import com.home.accounting.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class ExpenseTransformer {
    private AccountService accountService;
    private CategoryService categoryService;

    public ExpenseDto convertToDto(Expense expense) {
        return new ExpenseDto(
                expense.getId(),
                expense.getDate(),
                Double.toString(expense.getAmount()),
                expense.getComment(),
                Long.toString(expense.getAccount().getId()),
                Long.toString(expense.getCategory().getId())
        );
    }

    public Expense convertToEntity(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setId(expenseDto.getId());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(Double.parseDouble(expenseDto.getAmount()));
        expense.setComment(expenseDto.getComment());
        expense.setAccount(accountService.readById(Long.parseLong(expenseDto.getAccount())));
        expense.setCategory(categoryService.readById(Long.parseLong(expenseDto.getCategory())));
        return expense;
    }
}
