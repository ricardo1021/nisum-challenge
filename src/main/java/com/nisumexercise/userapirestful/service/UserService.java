package com.nisumexercise.userapirestful.service;

import com.nisumexercise.userapirestful.dto.ServiceResponse;
import com.nisumexercise.userapirestful.dto.UserRequest;
import com.nisumexercise.userapirestful.dto.UserResponse;

import java.util.List;

public interface UserService {

    ServiceResponse createUpdateUser(UserRequest userRequest);

    List<ServiceResponse> getAllUsers();

    ServiceResponse deleteUserByEmail(String email);

    ServiceResponse getUserByEmail(String email);
}
