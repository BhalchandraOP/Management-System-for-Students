package com.bhalchandra.studentregistrationmvc.security;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bhalchandra.studentregistrationmvc.model.Users;

public class CustomUserDetails implements UserDetails {

	private final Users user;

	public CustomUserDetails(Users user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(
	            new SimpleGrantedAuthority("ROLE_" + user.getRole())
	        );
	}

	@Override
	public @Nullable String getPassword() {
		  return user.getPassword();
	}

	@Override
	public String getUsername() {
		 return user.getUsername();
	}
	
	 @Override
    public boolean isAccountNonExpired() {
        return true;
    }
	 
	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
}
