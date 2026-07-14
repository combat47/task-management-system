package com.amir.taskmanager.controller;


import com.amir.taskmanager.dto.CreateTaskRequest;
import com.amir.taskmanager.model.Role;
import com.amir.taskmanager.model.User;
import com.amir.taskmanager.repository.UserRepository;
import com.amir.taskmanager.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TaskControllerIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        if (userRepository.findByUsername("amir1").isEmpty()) {
            User user = new User();
            user.setUsername("amir1");
            user.setPassword(passwordEncoder.encode("12345678"));
            user.setRole(Role.ADMIN);

            userRepository.save(user);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldReturnTasks() throws Exception{

        mockMvc.perform(get("/tasks")
                .header("Authorization", "Bearer " + getToken()))
                .andExpect(status().isOk());

    }

    @Test
    void shouldCreateTask() throws Exception {

        CreateTaskRequest request =
                new CreateTaskRequest(
                        "Integration Task",
                        "Created by ObjectMapper",
                        1L
                );

        mockMvc.perform(post("/tasks")
                        .header("Authorization", "Bearer " + getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect((status().isOk()));
    }

    private String getToken() {
        return jwtService.generateToken("amir1");
    }
}
