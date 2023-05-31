package com.spring.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChange {
	
	private String email;
	private String password;
	private String cpassword;

}
