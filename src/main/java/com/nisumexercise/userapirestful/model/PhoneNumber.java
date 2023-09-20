package com.nisumexercise.userapirestful.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="t_phone")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;


}
