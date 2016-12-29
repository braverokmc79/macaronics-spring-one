package com.example.wbe04.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.wbe04.util.password.EncodPassword;
import com.example.wbe04.util.password.PasswordEncoding;
import com.example.wbe04.util.password.SHAPasswordEncoder;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class PasswordEncoderTest {

    private static final Logger logger =LoggerFactory.getLogger(PasswordEncoderTest.class);
	

	
	@Test
	public void passTest() throws Exception{
		
		String dbpass =EncodPassword.dbSavePassword("1234");
		
		logger.info("DB에 저장 될 암호 ex) 1234: " + dbpass);
		// EncodPassword.dbPassword() 만 호출해서 반환 값만 DB 에 저장하면 된다.
		// 같은 값이 들어가도 반환 된 값은 랜덤하게 출력 된다. 
		
		// 테스트 로그인 할 유저  매치 값 비교하기  true 로 나오면 설정 완료
		logger.info("값 비교: " + EncodPassword.getPasswordEncoding().matches("1234", dbpass));
		
		
	}
	
}
