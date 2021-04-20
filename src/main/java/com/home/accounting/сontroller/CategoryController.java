package com.home.accounting.сontroller;

import com.home.accounting.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/of/expense/all")
    public String getAllOfExpense(Model model, Principal principal) {
        model.addAttribute("categories", categoryService.getCategoriesOfExpenseByUserEmail(principal.getName()));
        return "categories-of-expense-list";
    }

    @GetMapping("/of/income/all")
    public String getAllOfIncome(Model model, Principal principal) {
        model.addAttribute("categories", categoryService.getCategoriesOfIncomeByUserEmail(principal.getName()));
        return "categories-of-income-list";
    }
}
