package com.home.accounting.service.impl;

import com.home.accounting.model.User;
import com.home.accounting.repository.UserRepository;
import com.home.accounting.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        if(userRepository.getUserByEmail(user.getEmail()) != null)
            return null;
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("User is invalid");
        }
    }

    @Override
    public User readById(long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("User with id " + id + " not found");
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }
}
