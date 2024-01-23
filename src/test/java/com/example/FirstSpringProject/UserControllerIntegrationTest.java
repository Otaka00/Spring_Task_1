package com.example.FirstSpringProject;

import com.example.FirstSpringProject.rest.UserController;
import com.example.FirstSpringProject.service.UserRepository;
import com.example.FirstSpringProject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @MockBean
    UserRepository userRepository;
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private UserService service;
    private static List<User> usersList = new ArrayList<User>();

    User user1 = new User("alex", "145", 47);
    User user2 = new User("fati", "146", 48);
    User user3 = new User("ziad", "147", 49);

    @Test
    public void givenUsers_whenGetUsers_thenStatus200() throws Exception {

        // Given
        given(service.getAllUsers()).willReturn(Collections.emptyList());
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        Mockito.when(userRepository.findAll()).thenReturn(usersList);

        String content = mvc.perform(MockMvcRequestBuilders.get("/api/userRecords")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response Content: " + content);

        // When & Then
       mvc.perform(MockMvcRequestBuilders.get("/api/userRecords")
                       .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("alex")))
       ;
    }
}
