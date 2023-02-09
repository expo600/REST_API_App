package com.ryzhov_andrei.rest_api.service.impl;

import com.ryzhov_andrei.rest_api.repository.UserRepository;
import com.ryzhov_andrei.rest_api.repository.impl.UserRepositoryImpl;
import com.ryzhov_andrei.rest_api.model.User;
import com.ryzhov_andrei.rest_api.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
