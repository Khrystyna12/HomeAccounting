package com.home.accounting.сontroller;

import com.home.accounting.dto.IncomeDto;
import com.home.accounting.dto.IncomeTransformer;
import com.home.accounting.model.Income;
import com.home.accounting.service.AccountService;
import com.home.accounting.service.CategoryService;
import com.home.accounting.service.IncomeService;
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
@RequestMapping("/incomes")
@AllArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @GetMapping("/create")
    public String create(Model model, Principal principal) {
        model.addAttribute("income", new IncomeDto());
        model.addAttribute("accounts", accountService.getByUserEmail(principal.getName()));
        model.addAttribute("categories", categoryService.getCategoriesOfIncomeByUserEmail(principal.getName()));
        return "create-income";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("income") IncomeDto incomeDto, BindingResult result,
                         Model model, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("accounts", accountService.getByUserEmail(principal.getName()));
            model.addAttribute("categories", categoryService.getCategoriesOfIncomeByUserEmail(principal.getName()));
            return "create-income";
        }
        Income income = (new IncomeTransformer(accountService, categoryService)).convertToEntity(incomeDto);
        incomeService.create(income);
        return "redirect:/incomes/all";
    }

    @GetMapping("/all")
    public String getAll(Model model, Principal principal) {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00 UAH");
        model.addAttribute("incomes", incomeService.getByUserEmail(principal.getName()));
        model.addAttribute("decimalFormat", decimalFormat);
        return "incomes-list";
    }
}
