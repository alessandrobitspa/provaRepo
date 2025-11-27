package com.bit.h2SimpleBackendLogin.service;

import com.bit.h2SimpleBackendLogin.model.UserModel;
import com.bit.h2SimpleBackendLogin.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel signup(String username, String password) {
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password); // ⚠️ In un'app reale andrebbe cifrata!
        return userRepository.save(user);
    }

    public boolean login(String username, String password) {
        Optional<UserModel> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}