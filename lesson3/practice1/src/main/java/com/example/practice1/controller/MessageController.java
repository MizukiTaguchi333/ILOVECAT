package com.example.practice1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.practice1.model.Message;
import com.example.practice1.model.User;
import com.example.practice1.service.MessageService;
import com.example.practice1.service.UserService;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String messageList(Model model) {
        model.addAttribute("messages", messageService.findAllMessages());
        return "messages/list";
    }
    
    @GetMapping("/add")
    public String addMessageForm(Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("users", userService.findAllUsers());
        return "messages/add-message";
    }
    @PostMapping("/add")
    public String addMessage(Message message, @RequestParam("userList") List<Long> userList) {
        List<User> userSet = userList.stream()
            .map(id -> new User(id))
            .collect(Collectors.toList());
        messageService.saveMessageWithUsers(message, userSet);
        return "redirect:/messages";
    }
}
