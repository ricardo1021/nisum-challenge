package com.nisumexercise.userapirestful.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private boolean isActive;


    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumberList;

}
