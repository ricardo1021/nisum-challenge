package com.nisumexercise.userapirestful.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisumexercise.userapirestful.ApplicationConfig;
import com.nisumexercise.userapirestful.dto.UserRequest;
import com.nisumexercise.userapirestful.repository.UserRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class UserControllerTest {

    private static final String USER_PATH = "/api/user";
    private static String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSaWNhcmRvIiwiZXhwIjoxNjk1Mjc5ODgzLCJpYXQiOjE2OTUyNDM4ODN9.0o42I6RH7QFmhXclo7s3DxpbYda8_1322YVaFoMtBYk";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;



    @Test
    void shouldFetchAllUsers() throws Exception {
        UserRequest userRequest = getUserRequest();
        String userRequestToString = objectMapper.writeValueAsString(userRequest);

        this.mockMvc.perform(MockMvcRequestBuilders.get(USER_PATH)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].user.name", Is.is("Ricardo")))
                .andExpect(jsonPath("$[0].user.email", Is.is("ricardo@gmail.com")));

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void shouldGetUserSuccessfully() throws Exception {

        UserRequest userRequest = getUserRequest();
        String userRequestToString = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(MockMvcRequestBuilders.get(USER_PATH + "/getUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN)
                        .content(userRequestToString))
                .andExpect(jsonPath("$.user.name", Is.is("Ricardo")));
    }

    @Test
    void shoulGetUserNotExists() throws Exception {
        UserRequest userRequest = getUserRequest();
        userRequest.setEmail("ric@gmail.com");
        String userRequestToString = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(MockMvcRequestBuilders.get(USER_PATH + "/getUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN)
                        .content(userRequestToString))
                .andExpect(status().isNotFound());
    }



    @Test
    void shouldCreateUser() throws Exception {
        UserRequest userRequest = getUserRequest();
        userRequest.setEmail("edison@test.com");
        String userRequestToString = objectMapper.writeValueAsString(userRequest);
        mockMvc.perform(MockMvcRequestBuilders.post(USER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_TOKEN)
                        .content(userRequestToString))
                .andExpect(status().isCreated()
                );

        assertEquals(2, userRepository.findAll().size());

    }

    private UserRequest getUserRequest() {

        return UserRequest.builder()
                .name("Ricardo")
                .email("ricardo@gmail.com")
                .password("Pas1!")
                .build();

    }


}
