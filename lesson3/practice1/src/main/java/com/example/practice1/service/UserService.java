package com.example.practice1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice1.model.Message;
import com.example.practice1.model.User;
import com.example.practice1.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUserWithMessages(User user, List<Message> messages) {
        user.setCommentList(messages);
        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void updateUserWithMessages(User updateUser, List<Message> updateMessages) {
        User existingUser = userRepository.findById(updateUser.getId())
            .orElseThrow(() -> new EntityNotFoundException("not found"));
        existingUser.setName(updateUser.getName());
        existingUser.setCommentList(updateMessages);
        userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = findUserById(userId)
            .orElseThrow(() -> new EntityNotFoundException("not found"));
        user.getCommentList().clear();
        userRepository.delete(user);
    }
}
