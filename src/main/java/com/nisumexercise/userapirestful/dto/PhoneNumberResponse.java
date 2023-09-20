package com.nisumexercise.userapirestful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberResponse {

    private String number;
    private String cityCode;
    private String countryCode;
}
