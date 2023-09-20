package com.nisumexercise.userapirestful.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private boolean isUpdate;
    private List<PhoneNumberRequest> phones;



}
