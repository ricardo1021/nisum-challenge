package com.nisumexercise.userapirestful.controller;

import com.nisumexercise.userapirestful.dto.ServiceResponse;
import com.nisumexercise.userapirestful.dto.UserRequest;
import com.nisumexercise.userapirestful.exception.ResourceNotFoundException;
import com.nisumexercise.userapirestful.exception.UserException;
import com.nisumexercise.userapirestful.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;


    @ApiOperation("Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully create User"),
            @ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> createUser(@RequestBody UserRequest userRequest) {
        userRequest.setUpdate(Boolean.FALSE);
        try {
            return new ResponseEntity<>(userService.createUpdateUser(userRequest), HttpStatus.CREATED);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(new ServiceResponse(resourceNotFoundException.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (UserException userException) {
            return new ResponseEntity<>(new ServiceResponse(userException.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update User"),
            @ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> updateUser(@RequestBody UserRequest userRequest) {
        userRequest.setUpdate(Boolean.TRUE);
        try {

            return new ResponseEntity<>(userService.createUpdateUser(userRequest), HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(new ServiceResponse(resourceNotFoundException.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (UserException userException) {
            return new ResponseEntity<>(new ServiceResponse(userException.getMessage()), HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation("Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully get users"),
            @ApiResponse(responseCode = "404", description = "Not found users")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation("Delete user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete User"),
            @ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceResponse> deleteUserByEmail(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(userService.deleteUserByEmail(userRequest.getEmail()), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ServiceResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Get user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully search User"),
            @ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    @GetMapping("/getUser")
    public ResponseEntity<ServiceResponse> getUserByEmail(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(userService.getUserByEmail(userRequest.getEmail()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ServiceResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }


    }

}
