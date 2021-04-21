package com.home.accounting.сontroller;

import com.home.accounting.dto.ExpenseDto;
import com.home.accounting.dto.ExpenseTransformer;
import com.home.accounting.model.Account;
import com.home.accounting.model.Expense;
import com.home.accounting.service.AccountService;
import com.home.accounting.service.CategoryService;
import com.home.accounting.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.DecimalFormat;

@Controller
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @GetMapping("/create")
    public String create(Model model, Principal principal) {
        model.addAttribute("expense", new ExpenseDto());
        model.addAttribute("accounts", accountService.getByUserEmail(principal.getName()));
        model.addAttribute("categories", categoryService.getCategoriesOfExpenseByUserEmail(principal.getName()));
        return "create-expense";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("expense") ExpenseDto expenseDto, BindingResult result,
                         Model model, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("accounts", accountService.getByUserEmail(principal.getName()));
            model.addAttribute("categories", categoryService.getCategoriesOfExpenseByUserEmail(principal.getName()));
            return "create-expense";
        }
        Expense expense = (new ExpenseTransformer(accountService, categoryService)).convertToEntity(expenseDto);
        //check if amount of expense is not 0 or more that amount of account
        Account accountOfExpense = expense.getAccount();
        if (expense.getAmount() == 0 || accountOfExpense.getAmount() < expense.getAmount()) {
            model.addAttribute("accounts", accountService.getByUserEmail(principal.getName()));
            model.addAttribute("categories", categoryService.getCategoriesOfExpenseByUserEmail(principal.getName()));
            return "redirect:/expenses/create?error";
        }
        expenseService.create(expense);
        //update amount of account
        accountOfExpense.setAmount(accountOfExpense.getAmount() - expense.getAmount());
        accountService.update(accountOfExpense);
        return "redirect:/expenses/all";
    }

    @GetMapping("/all")
    public String getAll(Model model, Principal principal) {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00 UAH");
        model.addAttribute("expenses", expenseService.getByUserEmail(principal.getName()));
        model.addAttribute("decimalFormat", decimalFormat);
        return "expenses-list";
    }
}
