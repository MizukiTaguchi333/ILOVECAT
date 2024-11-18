package com.example.fukushu2.model;

import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@SQLRestriction("is_deleted = false")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private boolean isDeleted = false;

    @ManyToMany(mappedBy = "users")
    private Set<TalkRoom> talkRooms;

    public User(){}

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, boolean isDeleted, Set<TalkRoom> talkRooms) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.talkRooms = talkRooms;
    }

    public void setTalkRooms(Set<TalkRoom> talkRooms) {
        this.talkRooms = talkRooms;
    }
    public Set<TalkRoom> getTalkRooms() {
        return talkRooms;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isDeleted() {
        return isDeleted;
    }


}
