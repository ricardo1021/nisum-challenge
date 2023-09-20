package com.nisumexercise.userapirestful.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @JsonIgnore
    private String id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Date created;
    private Date modified;


    private List<PhoneNumberResponse> phoneNumberList;

}
