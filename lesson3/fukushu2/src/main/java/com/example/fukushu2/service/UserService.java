package com.example.fukushu2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fukushu2.model.User;
import com.example.fukushu2.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void updateUser(User updateUser) {
        userRepository.save(updateUser);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
