package com.bhalchandra.studentregistrationmvc.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bhalchandra.studentregistrationmvc.model.Users;
import com.bhalchandra.studentregistrationmvc.repo.UserRepo;
import com.bhalchandra.studentregistrationmvc.security.CustomUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService{

	private final UserRepo repo;

	public MyUserDetailsService(UserRepo repo){
	    this.repo = repo;
	}
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		 Users user =
	                repo.findByUsername(username)
	                    .orElseThrow(() ->
	                            new UsernameNotFoundException(
	                                    "User Not Found"));

	        return new CustomUserDetails(user);	
	        
	}

	
}
