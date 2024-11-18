package com.example.practice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice1.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
    
}
