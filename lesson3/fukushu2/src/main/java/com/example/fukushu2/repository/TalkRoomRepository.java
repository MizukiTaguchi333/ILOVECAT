package com.example.fukushu2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fukushu2.model.TalkRoom;

@Repository
public interface TalkRoomRepository extends JpaRepository<TalkRoom, Long>{
    
}
