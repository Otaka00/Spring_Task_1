package com.example.FirstSpringProject.service;


import com.example.FirstSpringProject.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private static List<User> list = new ArrayList<User>();

    @PostConstruct
    public void init() {
        User u1 = new User("Ahmad", "123", 25);
        User u2 = new User("Omar", "15", 25);
        u2.setAge(30);
        u2.setId("300");
        User u3 = new User("Ali", "125", 25);

        list.add(u1);
        list.add(u2);
        list.add(u3);
    }
    public List<User> getAllUsers() {
        for(User i : list)
            System.out.println(i.toString());
        return list;
    }

}


