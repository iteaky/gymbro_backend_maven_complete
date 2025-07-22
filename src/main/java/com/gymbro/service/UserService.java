package com.gymbro.service;

import com.gymbro.model.User;
import com.gymbro.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User register(String email, String password, String name) {
        if (findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким email  уже существует");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // В реальном проекте нужно хэшировать пароль!
        user.setName(name);
        return userRepository.save(user);
    }

    public UUID login(String email, String password) {
        Optional<User> userOpt = findByEmail(email);
        if (userOpt.isEmpty() || !userOpt.get().getPassword().equals(password)) {
            throw new IllegalArgumentException("Неверный email или пароль");
        }
        return userOpt.get().getId();
    }
}