package com.example.practice1.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
        name = "message_user",
        joinColumns = @JoinColumn(name = "message_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList;
    

    public Message(){}
    public Message(Long id) {
        this.id = id;
    }

    public Message(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Long getId() {
        return id;
    }
    public String getComment(){
        return comment;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    public List<User> getUserList() {
        return userList;
    }
}
