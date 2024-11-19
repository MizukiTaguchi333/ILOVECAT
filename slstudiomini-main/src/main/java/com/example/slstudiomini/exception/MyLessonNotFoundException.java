package com.example.slstudiomini.exception;

import jakarta.persistence.NoResultException;

public class MyLessonNotFoundException extends NoResultException {
    // private Long id;
    // public MyLessonNotFoundException(String message,Long id){
    //     super(message);
    //     this.id=id;
    // }
    // public Long getId() {
    //     return id;
    // }
    public MyLessonNotFoundException(String message) {
        super(message);
    }
    
}