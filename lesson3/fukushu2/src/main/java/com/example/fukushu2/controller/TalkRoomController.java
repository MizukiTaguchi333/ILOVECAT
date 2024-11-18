package com.example.fukushu2.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fukushu2.model.TalkRoom;
import com.example.fukushu2.model.User;
import com.example.fukushu2.service.TalkRoomService;
import com.example.fukushu2.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/talkRooms")
public class TalkRoomController {
    @Autowired
    private TalkRoomService talkRoomService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listTalkRooms(Model model) {
        List<TalkRoom> talkRooms = talkRoomService.findAllTalkRooms();
        model.addAttribute("talkRooms", talkRooms);
        return "talkrooms/list";
    }

    @GetMapping("/add")
    public String addTalkRoom(Model model) {
        model.addAttribute("talkRoom", new TalkRoom());
        model.addAttribute("users", userService.findAllUsers());
        return "talkrooms/add-talkroom";
    }

    @PostMapping("/add")
    public String addTalkRoom(TalkRoom talkRoom, @RequestParam(name="users") List<Long> users) {
        Set<User> bookSet = users.stream()
            .map(id -> new User(id))
            .collect(Collectors.toSet());
        talkRoomService.saveTalkRoomWithUsers(talkRoom, bookSet);
        return "redirect:/talkRooms";
    }

    @GetMapping("/edit/{id}")
    public String updateTalkRoom(@PathVariable("id") Long id, Model model) {
        TalkRoom talkRoom = talkRoomService.findTalkRoomById(id)
            .orElseThrow(() -> new EntityNotFoundException("not found"));
        model.addAttribute("talkRoom", talkRoom);
        model.addAttribute("users", userService.findAllUsers());
        return "talkRooms/edit";
    }

    @PostMapping("/edit")
    public String updateTalkRoom(TalkRoom talkRoom, @RequestParam(name = "users") List<Long> users) {
        Set<User> userSet = users.stream()
            .map(userId -> new User(userId))
            .collect(Collectors.toSet());
        talkRoomService.updateTalkRoomWithUsers(talkRoom, userSet);
        return "redirect:/talkRooms";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteTalkRoom(@PathVariable("id") Long id) {
        talkRoomService.deleteTalkRoom(id);
        return "redirect:/talkRooms";
    }
}
