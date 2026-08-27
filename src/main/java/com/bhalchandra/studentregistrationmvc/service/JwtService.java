package com.bhalchandra.studentregistrationmvc.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String secret;
	
	
	public JwtService(@Value("${jwt.secret}") String secret) {
		this.secret=secret;
	}

	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
		        .subject(userDetails.getUsername())
		        .issuedAt(new Date())
		        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
		        .signWith(getSigningKey())
		        .compact();
	}
	
	
	public String extractUsername(String token) {

	    return Jwts.parser() // I want to read This Token
	            .verifyWith(getSigningKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getSubject();
	}
	
	private Date extractExpiration(String token) {

	    return Jwts.parser()
	            .verifyWith(getSigningKey())
	            .build()
	            .parseSignedClaims(token)
	            .getPayload()
	            .getExpiration();
	}
	
	private boolean isTokenExpired(String token) {

	    return extractExpiration(token)
	            .before(new Date());
	}
	
	private SecretKey getSigningKey() {

	    return Keys.hmacShaKeyFor(
	        Decoders.BASE64.decode(secret)
	    );
	}
	
	public boolean isTokenValid(
	        String token,
	        UserDetails userDetails) {

	    String username = extractUsername(token);

	    return username.equals(userDetails.getUsername())
	            && !isTokenExpired(token);
	}
	
}
