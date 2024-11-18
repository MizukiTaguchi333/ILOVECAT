package com.example.fukushu2.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fukushu2.model.TalkRoom;
import com.example.fukushu2.model.User;
import com.example.fukushu2.repository.TalkRoomRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TalkRoomService {
    @Autowired
    private TalkRoomRepository talkRoomRepository;

    public List<TalkRoom> findAllTalkRooms() {
        return talkRoomRepository.findAll();
    }
    public TalkRoom saveTalkRoom(TalkRoom talkRoom) {
        return talkRoomRepository.save(talkRoom);
    }

    public Optional<TalkRoom> findTalkRoomById(Long id) {
        return talkRoomRepository.findById(id);
    }

    public void saveTalkRoomWithUsers(TalkRoom talkRoom, Set<User> users) {
        talkRoom.setUsers(users);
        talkRoomRepository.save(talkRoom);
    }

    @Transactional
    public void updateTalkRoomWithUsers(TalkRoom updateTalkRoom, Set<User> updateUsers) {
        TalkRoom existingTalkRoom = talkRoomRepository.findById(updateTalkRoom.getId())
            .orElseThrow(() -> new EntityNotFoundException("store not found"));
        
            existingTalkRoom.setName(updateTalkRoom.getName());
            existingTalkRoom.setUsers(updateUsers);
            talkRoomRepository.save(existingTalkRoom);
    }

    @Transactional
    public void deleteTalkRoom(Long talkRoomId) {
        TalkRoom talkRoom = findTalkRoomById(talkRoomId)
            .orElseThrow(() -> new EntityNotFoundException("not found"));
        talkRoom.getUsers().clear();
        talkRoomRepository.delete(talkRoom);
    }
}
