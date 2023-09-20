package com.nisumexercise.userapirestful.repository;

import com.nisumexercise.userapirestful.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    User findByName(String name);
}
