package com.example.practice1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.practice1.model.Message;
import com.example.practice1.model.User;
import com.example.practice1.service.MessageService;
import com.example.practice1.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/list";
    }
    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("messages", messageService.findAllMessages());
        return "users/add-user";
    }

    @PostMapping("/add")
    public String addUser(User user, @RequestParam(name = "commentList") List<Long> messages) {
        List<Message> messageList = messages.stream()
            .map(id -> new Message(id))
            .collect(Collectors.toList());

        userService.saveUserWithMessages(user, messageList);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
