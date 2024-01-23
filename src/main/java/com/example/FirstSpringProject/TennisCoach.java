package com.example.FirstSpringProject;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{
    @Override
    public String getWorkout() {
        return "Playing Tennis";
    }
    public TennisCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

}
