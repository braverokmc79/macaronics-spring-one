package com.example.wbe04.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//aspectjweaver(1.6.11)와 aspectjrt(1.6.8)  을 mavenrepository 에서 검색해서 pom.xl 넣는다.
//******************* 중요
/*	   <!-- aop의 설정을 통한 자동적인 Proxy 객체 설정 --> -->
		<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
		<context:component-scan base-package="com.example.wbe04.*" />
//<aop:config> 추가	- 네임스페이스 aop 체크	
//component 스켄 전에 붙여야 한다.	안되면 root-context.xml 과 servlet-context.xml 양쪽 모두 맨 상단위에 추가
* 
* */
//종단 관심 - 핵심적인 비즈니스 로직
//횡단 관심(Aspect) -공통적인 로직
//Advice - 횡단관심을 모든 코드에서 처리하지 않고 공통적으로 지원할수 있도록
//처리하는 코드 
//@Controller 컨트롤빈
//@Service 서비스빈
//@Repository dao빈
//@Component 기타빈

@Component //스프링에서 관리하는 빈
@Aspect //스프링에서 관리하는 aop bean
public class LogAdvice {
//포인트 컷(실행시점) -Before-실행 전, After -실행 후 , Around -실행 전후
//컨트롤러의 모든 method 실행 전후에 logPrint method 가 호출됨	

//	controller.. 점 두개의미는 controller 다음 모든 패키지 -> xxxController 클래스 의 .* 의미는  모든 메소드 
	
	private static final Logger logger=LoggerFactory.getLogger(LogAdvice.class);
	
	@Around(
	 "execution(* com.example.wbe04.controller..*Controller.*(..))"
	 + " or execution( * com.example.wbe04.service..*Impl.*(..))"
	 + " or execution( * com.example.wbe04.model..dao.*Impl.*(..))")
	public Object logPrint(ProceedingJoinPoint joinpoint) throws Throwable{
		
		//시스템의 현재시각
		long startTime =System.currentTimeMillis();
		Object result =joinpoint.proceed();
		
		//클래스 이름 
		String type =joinpoint.getSignature().getDeclaringTypeName();
		//method 이름
		String method=joinpoint.getSignature().getName();
		//매개 변수
		String args =Arrays.toString(joinpoint.getArgs());
		
		
		
		logger.info("클래스 :"+ type);
		logger.info("method :"+ method);
		logger.info("매개변수 :"  + args);
		
		long endTime =System.currentTimeMillis();
		logger.info("실행시간 : " + (endTime-startTime));
		
		//return joinpoint.proceed();// 컨트롤러의 method가 실행됨 -- 현재는 before로 적용 
		//만약joinpoint.proceed() 중간에 있으면 Around 적용
		logger.info("=====================구분선========================================");
		return result;
	}
	

	
	
	
	
}




