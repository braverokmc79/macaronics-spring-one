package com.example.wbe04.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.wbe04.model.board.dao.BoardDAO;
import com.example.wbe04.model.board.dto.BoardDTO;
import com.example.wbe04.util.mysql.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {

	
	@Inject
	private BoardDAO boardDAO;
	
	
     private static final Logger logger =LoggerFactory.getLogger(BoardDAOTest.class);
	
	
//	@Test
	public void testListPage() throws Exception{
		
		int page =3;
	
		List<BoardDTO> list=boardDAO.listPage(page);
		
		for(BoardDTO dto :list){
			logger.info(dto.getIdx() + " : " + dto.getContent());
		}
		
		
	}
	
	
//	@Test
	public void testListCriteria() throws Exception{
		Criteria cri =new Criteria();
		cri.setPage(1);
		cri.setPerPageNum(20);
		
		List<BoardDTO> list =boardDAO.listCritera(cri);
		for(BoardDTO dto :list){
			logger.info(dto.getIdx() + " : " + dto.getContent());
		}
		
	}
	
	
	@Test
	public void testURI() throws Exception{
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
		
	}
	
	
	
	
}
