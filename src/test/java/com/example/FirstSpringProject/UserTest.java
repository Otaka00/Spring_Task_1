package com.example.FirstSpringProject;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void addUserIdwithLengthlessthan3(){
        User u = new User();
        assertThrows(IllegalArgumentException.class, () ->{
            u.setId("12");
        });
    }

}