package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(User user) {
        // Проверка, существует ли уже пользователь с таким именем
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует!");
        }
    
        // Хеширование пароля
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        // По умолчанию устанавливаем роль USER
        user.setRole(User.Role.USER);
    
        // Сохранение пользователя в базу данных
        User savedUser = userRepository.save(user);
    
        return savedUser;
    }
    public User.Role getUserRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден!"));
        return user.getRole();
    }

    public void setUserRole(Long userId, User.Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден!"));
        user.setRole(newRole);
        userRepository.save(user);
    }
}