package com.spring.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	User findByEmailOrId(String email,Integer id);

}
