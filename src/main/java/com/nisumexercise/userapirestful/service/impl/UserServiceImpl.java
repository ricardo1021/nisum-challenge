package com.nisumexercise.userapirestful.service.impl;

import com.nisumexercise.userapirestful.dto.ServiceResponse;
import com.nisumexercise.userapirestful.dto.UserRequest;
import com.nisumexercise.userapirestful.exception.ResourceNotFoundException;
import com.nisumexercise.userapirestful.exception.UserException;
import com.nisumexercise.userapirestful.model.PhoneNumber;
import com.nisumexercise.userapirestful.model.User;
import com.nisumexercise.userapirestful.repository.UserRepository;
import com.nisumexercise.userapirestful.service.UserService;
import com.nisumexercise.userapirestful.validation.EmailValidation;
import com.nisumexercise.userapirestful.validation.PasswordValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ServiceResponse createUpdateUser(UserRequest userRequest) {

        if (!EmailValidation.patternMatches(userRequest.getEmail())) {
            throw new ResourceNotFoundException("This email IS NOT CORRECT. Please check!");
//            return new ServiceResponse("");
        }

        if (!PasswordValidation.patternMatches(userRequest.getPassword())) {
            throw new ResourceNotFoundException("This password IS NOT CORRECT. Please check!");
        }

        if (findUserByEmailIsPresent(userRequest.getEmail()) && !userRequest.isUpdate()) {
            throw new UserException("This email already exist.");
        } else if (userRequest.isUpdate()) {
            try {
                User user = findUserByEmail(userRequest.getEmail());
                user.setModified(new Date());
                user.setLastLogin(new Date());
                user.setActive(Boolean.TRUE);

                List<PhoneNumber> phoneNumberList = userRequest.getPhones()
                        .stream()
                        .map(phoneNumberRequest -> modelMapper.map(phoneNumberRequest, PhoneNumber.class))
                        .collect(Collectors.toList());

                user.setPhoneNumberList(phoneNumberList);
                user = userRepository.save(user);

                return modelMapper.map(user, ServiceResponse.class);
            } catch (Exception e) {
                return new ServiceResponse(e.getMessage());
            }

        } else {
            User user = modelMapper.map(userRequest, User.class);
            user.setId(UUID.randomUUID().toString());
            user.setCreated(new Date());
            user.setModified(new Date());
            user.setLastLogin(new Date());
            user.setActive(Boolean.TRUE);

            if(CollectionUtils.isEmpty(userRequest.getPhones())) userRequest.setPhones(new ArrayList<>());

            List<PhoneNumber> phoneNumberList = userRequest.getPhones()
                    .stream()
                    .map(phoneNumberRequest -> modelMapper.map(phoneNumberRequest, PhoneNumber.class))
                    .collect(Collectors.toList());

            user.setPhoneNumberList(phoneNumberList);
            user = userRepository.save(user);

            return modelMapper.map(user, ServiceResponse.class);
        }
    }

    @Override
    public List<ServiceResponse> getAllUsers() {

        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> modelMapper.map(user, ServiceResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse deleteUserByEmail(String email) {
        try {
            User user = findUserByEmail(email);
            userRepository.delete(user);
            return new ServiceResponse("User deleted successfully");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Exception user not found");
        }
    }

    @Override
    public ServiceResponse getUserByEmail(String email) {
        try {
            User user = findUserByEmail(email);
            return modelMapper.map(user, ServiceResponse.class);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Exception user not found");
        }
    }

    private boolean findUserByEmailIsPresent(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Exception user not found"));
    }
}
