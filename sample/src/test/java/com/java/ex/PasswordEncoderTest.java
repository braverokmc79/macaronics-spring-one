package com.java.ex;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 2. 18.
 */

public class PasswordEncoderTest {

	@Test
	public void test() {
		String password = "1234";
		
	
		// SHA-256 암호화를 사용한다
		SHAPasswordEncoder shaPasswordEncoder = new SHAPasswordEncoder(512);  
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		
		
		PasswordEncoding passwordEncoding = new PasswordEncoding(shaPasswordEncoder);  //
		
		System.out.println("SHA 암호화: " + passwordEncoding.encode(password));
		System.out.println("SHA 비교: " + passwordEncoding.matches(password, passwordEncoding.encode(password)));
        
		
		//BCryptPasswordEncoder  여기서 PasswordEncoding 상속 받았다.  
		//스프링 시큐리티에서 기본적을 사용하는 암호화 방식으로 암호화가 될때마다 새로운 값을 생성한다. 
		//임의적인 값을 추가해서 암호화하지 않아도 된다. (salt 사용하지 않는다.)
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		passwordEncoding = new PasswordEncoding(passwordEncoder);

		
		//두번째 암호화 생성 암호 first2
		String first2=passwordEncoding.encode(password);
		
		System.out.println("1. BCrypt 암호화: " + passwordEncoding.encode(password));
		System.out.println("1. BCrypt 비교: " + passwordEncoding.matches(password, passwordEncoding.encode(password)));

		
		// 3번째  다시 BCrypt 암호화 암호 first3  최종적으로 DB  에 저장 될 값 은 first3
		String first3 =passwordEncoding.encode(first2);
		
		System.out.println("2. BCrypt 암호화: " + passwordEncoding.encode(password));
		System.out.println("2. BCrypt 비교: " + passwordEncoding.matches(password, passwordEncoding.encode(password)));
	
	
	// ********************************************************************************** 
		//DB에 저장 된 값  1234 의 암호화
	
		//로기인 유저 암호화
	//DB에 저장된 first3 값과 로그할 유저가 넣는  1234 값 비교
		System.out.println(" 반환 값 :" + dbPassword("1234"));
		System.out.println("로그인 확인: " + passwordEncoding.matches("1234", dbPassword("1234")) );
	
	}
	
	
	
	//DB 에 변경해서 저장될 암호
	public String dbPassword(String loginPassword){
		
		SHAPasswordEncoder shaPasswordEncoder = new SHAPasswordEncoder(512);  
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		PasswordEncoding passwordEncoding = new PasswordEncoding(shaPasswordEncoder);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		passwordEncoding = new PasswordEncoding(passwordEncoder);

		//세번째 암호화
		String passwod =passwordEncoding.encode(loginPassword);
		return passwod;
	}
	
	
	
	
}
