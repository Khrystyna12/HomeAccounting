package com.home.accounting.service;

import com.home.accounting.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User readById(long id);
    List<User> getAll();
}
