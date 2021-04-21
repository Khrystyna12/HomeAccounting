package com.home.accounting.сontroller;

import com.home.accounting.model.Account;
import com.home.accounting.model.Category;
import com.home.accounting.model.User;
import com.home.accounting.service.AccountService;
import com.home.accounting.service.CategoryService;
import com.home.accounting.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserService userService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "create-user";
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User newUser = userService.create(user);
        if(newUser == null)
            return "redirect:/users/create?error";
        //create default account
        Account defaultAccount = new Account();
        defaultAccount.setTitle("Account");
        defaultAccount.setAmount(0);
        defaultAccount.setUser(newUser);
        accountService.create(defaultAccount);
        //create default categories
        categoryService.create(new Category("Products", true, newUser));
        categoryService.create(new Category("Accounts", true, newUser));
        categoryService.create(new Category("Shopping", true, newUser));
        categoryService.create(new Category("Services", true, newUser));
        categoryService.create(new Category("Transport", true, newUser));
        categoryService.create(new Category("Health", true, newUser));
        categoryService.create(new Category("Entertainment", true, newUser));
        categoryService.create(new Category("Other expense", true, newUser));
        categoryService.create(new Category("Salary", false, newUser));
        categoryService.create(new Category("Other income", false, newUser));
        return "redirect:/accounts/all";
    }
}
