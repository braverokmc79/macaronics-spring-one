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



	@Override
	public void updateHit(int idx) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace +".updateHit", idx);
	}



	
	@Override
	public void reorderUpdate(int ref, int reorder) {	
		try{
			
			Map<String, Object> map =new HashMap<>();
			map.put("ref", ref); //게시물 그룹 번호
			map.put("reorder", reorder); //같은 게시물 내에서의 순서
			sqlSession.update(namespace+".reorderUpdate", map);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	@Override
	public void replyInsert(BoardDTO dto) {
		// TODO Auto-generated method stub
		try{
			sqlSession.insert(namespace +".replyInsert", dto);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	//마지막에 등록한 게시글 번호 가져오기
	@Override
	public int lastInsertIdx() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".lastInsertIdx");
	}


	//ref  업데이트 하기
	@Override
	public void refUpdate(int idx) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".refUpdate" , idx);
	}



	@Override
	public void boardDelete(int idx) {
		
		sqlSession.delete(namespace+".boardDelete", idx);
		
	}



	@Override
	public int replyExist(Integer ref, Integer reorder) {
		// TODO Auto-generated method stub
		Map<String, Object> map =new HashMap<>();
		map.put("ref", ref);
		map.put("reorder", reorder);
		
		return sqlSession.selectOne(namespace+".replyExist", map);
	}



	@Override
	public void boardUpdate(BoardDTO dto) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+ ".boardUpdate", dto);
	}	
	
	
	
	
	
}
