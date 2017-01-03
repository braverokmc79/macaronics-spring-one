package com.example.wbe04.util.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodPassword {

	private static PasswordEncoding passwordEncoding;
	
	
	//DB 에 변경해서 저장될 암호
	public static String dbSavePassword(String encodePasswod){
			
		PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();;
		passwordEncoding = new PasswordEncoding(passwordEncoder);
		return passwordEncoding.encode(encodePasswod);
	}
	
	
		
	public static PasswordEncoding getPasswordEncoding() {
		return passwordEncoding;
	}
	
	
}
