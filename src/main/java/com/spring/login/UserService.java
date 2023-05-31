package com.spring.login;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public boolean registerUser(User user) {

		Optional<User> emailEntry = userRepository.findByEmail(user.getEmail());

		if (emailEntry.isPresent())
			return false;
		else
			return userRepository.save(user) != null;
	}

	public boolean loginUser(User user) {
		Optional<User> emailEntry = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

		if (emailEntry.isPresent())
			return true;
		else
			return false;

	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUser(User user) {
		return userRepository.findByEmailOrId(user.getEmail(), user.getId());
	}

	public boolean passwordSet(PasswordChange pass) {
		if (pass.getPassword().equals(pass.getCpassword())) {

			User user = userRepository.findByEmail(pass.getEmail()).get();
			user.setPassword(pass.getPassword());
			userRepository.save(user);
			return true;
		} else {

			return false;
		}

	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}

	public User getById(int id) {
		return userRepository.findById(id).get();
	}

}
