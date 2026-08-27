package com.bhalchandra.studentregistrationmvc.security;

import java.security.SecureRandom;
import java.util.Base64;

public class base64 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		byte[] key = new byte[32];
		new SecureRandom().nextBytes(key);

		String secret = Base64.getEncoder().encodeToString(key);

		System.out.println(secret);
	}

}
