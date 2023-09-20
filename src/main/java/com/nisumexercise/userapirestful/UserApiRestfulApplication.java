package com.nisumexercise.userapirestful;

import com.nisumexercise.userapirestful.model.User;
import com.nisumexercise.userapirestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class UserApiRestfulApplication {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initUsers(){
		List<User> users = Stream.of(
				new User(UUID.randomUUID().toString(),"Ricardo","ricardo@gmail.com","pwd1", new Date(), new Date(),new Date(), Boolean.TRUE, new ArrayList<>())
		).collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApiRestfulApplication.class, args);
	}

}
