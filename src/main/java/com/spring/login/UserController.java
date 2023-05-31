package com.spring.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/register")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping("/process_register")
	public String processRegister(User user, Model model) {
		userService.registerUser(user);
		return "redirect:/list";
	}

	@RequestMapping("/list")
	public String allUserList(Model model) {
		model.addAttribute("alluser", userService.getAllUser());
		return "userlist";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping("/process_login")
	public String processLogin(User user) {
		return userService.loginUser(user) ? "loginSuccess" : "loginFail";
	}

	@RequestMapping("/search")
	public String search(Model model) {
		model.addAttribute("user", new User());
		return "search";
	}

	@RequestMapping("/process_search")
	public String searchUser(User searchedUser, Model model) {
		User user = userService.getUser(searchedUser);
		model.addAttribute("user", user);
		return "search";
	}

	@RequestMapping("/passreset")
	public String passReset(Model model) {
		model.addAttribute("pass", new PasswordChange());
		return "resetpass";
	}

	@RequestMapping("/process_pass")
	public String passProcess(PasswordChange pass, Model model) {
		return userService.passwordSet(pass) ? "redirect:/login" : "pass_change_fail";
	}

	@GetMapping("/updateUser/{id}")
	public String updateUser(@PathVariable(value = "id") int id, Model model) {
		model.addAttribute("user", userService.getById(id));
		return "update";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") int id) {
		userService.delete(id);
		return "redirect:/list";
	}

}
