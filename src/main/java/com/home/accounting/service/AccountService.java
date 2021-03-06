package com.home.accounting.service;

import com.home.accounting.model.Account;

import java.util.List;

public interface AccountService {
    Account create(Account account);
    Account readById(long id);
    Account update(Account account);
    List<Account> getByUserEmail(String email);
    List<Account> getAll();
}
