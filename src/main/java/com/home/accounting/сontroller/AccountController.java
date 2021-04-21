package com.home.accounting.сontroller;

import com.home.accounting.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.DecimalFormat;

@Controller
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/all")
    public String getAll(Model model, Principal principal) {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00 UAH");
        model.addAttribute("accounts", accountService.getByUserEmail(principal.getName()));
        model.addAttribute("decimalFormat", decimalFormat);
        return "accounts-list";
    }
}
