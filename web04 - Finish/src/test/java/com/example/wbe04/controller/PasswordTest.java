package com.example.wbe04.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.wbe04.util.encoder.EncodPassword;
import com.example.wbe04.util.encoder.PasswordEncoding;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class PasswordTest {

	@Inject
	PasswordEncoding passwordEncoding;
	
	//@Test
	public void test2() {
		// 1234 라 할지라도 매번 다른 값으로 랜덤하게 encoding 된다.
		String encode=passwordEncoding.encode("1234");
		// encode 를 DB에 저장 하면 된다.
		
		//값을 비교 하는 것은 matches() 를 사용해서
		//로그인 유저와 DB에 저장된 비번을 비교 해서 true 값 이면 비번이 일치 한다는 것이다.
		
		passwordEncoding.matches("1234", encode);
		
		System.out.println("passwordEncoding.encode(1234)" +encode);
		System.out.println("1234 비교: " + passwordEncoding.matches("1234", encode));
	}
	
	
	@Test
	public void passTest() throws Exception{
		
		String dbpass =EncodPassword.dbSavePassword("Knowledge876Knowledge876Knowledge876");
		
		System.out.println("DB에 저장 될 암호 ex) 1111: " + dbpass);
		// EncodPassword.dbPassword() 만 호출해서 반환 값만 DB 에 저장하면 된다.
		// 같은 값이 들어가도 반환 된 값은 랜덤하게 출력 된다. 
		
		// 테스트 로그인 할 유저  매치 값 비교하기  true 로 나오면 설정 완료
		System.out.println("값 비교: " + EncodPassword.getPasswordEncoding().matches("Knowledge876Knowledge876Knowledge876", dbpass));
		
		
	}
	
	
}
