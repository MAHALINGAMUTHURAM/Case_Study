package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.RoleDAO;
import com.dao.UserDAO;
import com.jwt.TokenGenerator;
import com.model.Role;
import com.model.User;


@RestController
public class AuthController {
	
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	RoleDAO roleDAO;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		List<Role> rolelist=new ArrayList<>();
		Role role=new Role();
		role.setRolename("USER");
		user.setRoles(rolelist);
		role.setUser(user);
		userDAO.save(user);
		roleDAO.save(role);
		
//		TokenGenerator token=new TokenGenerator();
//		token.generateToken(user.getUserId(), user.getPassword(), "USER");
//		String token1=token.getToken();
		return new ResponseEntity<>("user created",HttpStatus.OK);
	}
	
	@GetMapping("/getitems")
	public ResponseEntity<?> getUser()
	{
		return new ResponseEntity<String>("Hello",HttpStatus.OK);
	}
}
