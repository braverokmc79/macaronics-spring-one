package com.example.wbe04.model.mail.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.wbe04.model.mail.dto.MailDTO;

@Repository
public class MailDAOImpl implements MailDAO {

	@Inject
	SqlSession sqlSession;
	
	private final static String namespace="mail.mapper";
	
	@Override
	public void mailInsert(MailDTO mail) {
		

		
		
		sqlSession.insert(namespace+".mailInsert", mail);
	}

	
	
	
}
