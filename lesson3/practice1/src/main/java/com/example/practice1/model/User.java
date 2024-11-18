package com.example.practice1.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Message> comments;

    @ManyToMany(mappedBy = "userList")
    private List<Message> commentList = new ArrayList<>();

    public User(){}
    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, List<Message> commentList) {
        this.id = id;
        this.name = name;
        this.commentList = commentList;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setComments(List<Message> comments) {
        this.comments = comments;
    }
    public List<Message> getComments() {
        return comments;
    }

    public void setCommentList(List<Message> commentList) {
        this.commentList = commentList;
    }
    public List<Message> getCommentList() {
        return commentList;
    }
}

