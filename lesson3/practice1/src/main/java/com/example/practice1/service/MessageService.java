package com.example.practice1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice1.model.Message;
import com.example.practice1.model.User;
import com.example.practice1.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public void saveMessageWithUsers(Message message, List<User> userList) {
        message.setUserList(userList);
        messageRepository.save(message);
    }
}
