package com.example.fukushu2.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "talkRooms")
public class TalkRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @ManyToMany
    @JoinTable(
        name="talkRoom_user",
        joinColumns = @JoinColumn(name = "talkRoom_id"),
        inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private Set<User> users;

    public TalkRoom(){}
    public TalkRoom(Long id) {
        this.id = id;
    }

    public TalkRoom(Long id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<User> getUsers() {
        return users;
    }
}