package com.example.slstudiomini.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    
    @Scheduled(fixedRate = 5000) //fixes every 5 sec
    public void reportCurrentTime() {
        System.out.println("current time is " + System.currentTimeMillis());
    }
}
