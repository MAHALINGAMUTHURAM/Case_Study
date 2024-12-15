package com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.model.User;

@Service
public class CustomerUserDetailService implements UserDetailsService{
	
	@Autowired
	UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
//		List<User> userlist=userDAO.findAll();
		User user=userDAO.findById(username).get();
		
		List<SimpleGrantedAuthority> authorities=user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
		org.springframework.security.core.userdetails.User dbuser=new org.springframework.security.core.userdetails.User(user.getUserId(),user.getPassword(),authorities);
		return dbuser;
	}
	

}
