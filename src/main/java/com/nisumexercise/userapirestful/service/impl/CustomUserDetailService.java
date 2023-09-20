package com.nisumexercise.userapirestful.service.impl;

import com.nisumexercise.userapirestful.model.User;
import com.nisumexercise.userapirestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),new ArrayList<>());
    }
}
