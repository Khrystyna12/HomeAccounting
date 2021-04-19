package com.home.accounting.service.impl;

import com.home.accounting.model.Account;
import com.home.accounting.repository.AccountRepository;
import com.home.accounting.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Override
    public Account create(Account account) {
        try {
            return accountRepository.save(account);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Account cannot be 'null'");
        }
    }

    @Override
    public Account readById(long id) {
        Optional<Account> optional = accountRepository.findById(id);
            return optional.get();
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.isEmpty() ? new ArrayList<>() : accounts;
    }
}
