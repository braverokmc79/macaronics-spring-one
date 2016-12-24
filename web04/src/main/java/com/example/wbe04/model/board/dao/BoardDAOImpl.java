package com.example.wbe04.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.wbe04.model.board.dto.BoardDTO;
import com.example.wbe04.util.mysql.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	
	@Inject 
	private SqlSession sqlSession;
	
	private static final String namespace="board.Mapper";
	
	private static final Logger logger  =LoggerFactory.getLogger(BoardDAOImpl.class);
	
	
	@Override
	public List<BoardDTO> boardList(int start, int end) {
		List<BoardDTO> list=null;
		try {
			
			Map<String, Object> map =new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			logger.info(" start :" + start  + "  end : " + end);
			
			list=sqlSession.selectList(namespace+".boardList", map);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}

	

	@Override
	public void boardInsert(BoardDTO dto) {
		
		try {
			sqlSession.insert(namespace+".boardInsert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	@Override
	public void downCont(int idx) {
		try {
			sqlSession.update(namespace+".downCont", idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	@Override
	public int pageTotalCount() {
		
		return sqlSession.selectOne(namespace+".pageTotalCount");
	}



	
	//MySQL
	@Override
	public List<BoardDTO> listPage(int page) throws Exception {
		if(page <=0){
			page =1;
		}
		
		page  =(page -1)* 10;
		
		return sqlSession.selectList(namespace+".listPage", page);
	}


	//MySQL
	@Override
	public List<BoardDTO> listCritera(Criteria cri) throws Exception {
		
		return sqlSession.selectList(namespace+".listCritera", cri);
	}



	@Override
	public BoardDTO boardView(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".boardView", idx);
	}

	
	
	
	
	
	
	
}
