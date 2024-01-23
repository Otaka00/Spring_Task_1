package com.example.FirstSpringProject;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach{

    public String getWorkout(){
        return "Playing football";
    }

    public FootballCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
}
