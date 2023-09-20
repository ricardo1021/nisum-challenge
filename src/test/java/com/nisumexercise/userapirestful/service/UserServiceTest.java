package com.nisumexercise.userapirestful.service;

import com.nisumexercise.userapirestful.builder.UserBuilderTest;
import com.nisumexercise.userapirestful.dto.UserResponse;
import com.nisumexercise.userapirestful.exception.ResourceNotFoundException;
import com.nisumexercise.userapirestful.model.User;
import com.nisumexercise.userapirestful.repository.UserRepository;
import com.nisumexercise.userapirestful.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    public UserServiceImpl userService;

    @Mock
    public UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    @Test
    public void getAllUsers() {
        User user1 = UserBuilderTest.builder().build();
        List<User> userList = Arrays.asList(new User[]{user1});
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        userService.getAllUsers();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    public void getUserByIdSuccessfully() throws Exception {
        String email = "ricardo@gmail.com";
        User user1 = UserBuilderTest.builder().build();
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user1));
        userService.getUserByEmail(email);
        Mockito.verify(userRepository).findByEmail(email);
    }

    @Test
    public void getUserByIdNotExists() throws Exception {
        String email = "edison@gmail.com";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.getUserByEmail(email));
    }

    @Test
    public void deleteUserSuccessfully() throws Exception {
        String email = "ricardo@gmail.com";
        User user1 = UserBuilderTest.builder().build();
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user1));
        Mockito.doNothing().when(userRepository).delete(user1);

        userService.deleteUserByEmail(email);

        Mockito.verify(userRepository).findByEmail(email);
        Mockito.verify(userRepository).delete(user1);
    }

}
