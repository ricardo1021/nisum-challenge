package com.nisumexercise.userapirestful.builder;

import com.nisumexercise.userapirestful.model.PhoneNumber;
import com.nisumexercise.userapirestful.model.User;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserBuilderTest {
    @Builder
    public static User user(String id, Date lastLogin, Boolean active, Date modified,
                            Date created, List<PhoneNumber> phoneList, String name, String password) {

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLastLogin(lastLogin);
        user.setActive(active);
        user.setModified(modified);
        user.setEmail("ricardo@gmail.com");
        user.setCreated(created);
        user.setPhoneNumberList(phoneList);
        user.setName(name);
        user.setPassword(password);

        return user;


    }

    public static class UserBuilder {

        private String id = UUID.randomUUID().toString();
        private Date lastLogin = new Date();
        private Boolean active = Boolean.TRUE;
        private Date modified = new Date();
        private Date created = new Date();
        private List<PhoneNumber> phoneList = new ArrayList<>();
        private String name = "Ricardo";
        private String password = "Pwd1!";
    }
}
