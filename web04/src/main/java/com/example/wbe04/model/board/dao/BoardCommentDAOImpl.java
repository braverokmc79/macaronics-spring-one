package com.example.wbe04.model.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.wbe04.model.board.dto.BoardCommentDTO;

@Repository
public class BoardCommentDAOImpl implements BoardCommentDAO {

	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="boardComment.Mapper";
	
	
	private static final Logger logger=LoggerFactory.getLogger(BoardCommentDAOImpl.class);
	
	
	
	@Override
	public List<BoardCommentDTO> commentList(int board_idx) {
		List<BoardCommentDTO> list=null;
		try{
			
			list=sqlSession.selectList(namespace+".commentList", board_idx);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	
	@Override
	public void comment_insert(BoardCommentDTO commentDTO) {
		
		try{
			sqlSession.insert(namespace+".comment_insert", commentDTO);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
}
