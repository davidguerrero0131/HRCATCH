package com.HUSRTbdBiomedica.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "2022";
		String encodedPassword = encoder.encode(rawPassword);
		

		System.out.println(encodedPassword);
	}
	//$2a$10$6WNSgFxmH4DjgzwC.Ql3weVhzE0SaTfA4o46ZsBxPvRb89sRMWhN2
	//$2a$10$4AIlzK1AofSzbFm9IsdbD.AtdVfyfGow.k9UNv43e32.ajVm92q3q
	
	


}

