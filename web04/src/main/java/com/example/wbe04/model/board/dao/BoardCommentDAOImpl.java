package com.example.wbe04.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.wbe04.model.board.dto.BoardCommentDTO;
import com.example.wbe04.util.mysql.PageMaker;

@Repository
public class BoardCommentDAOImpl implements BoardCommentDAO {

	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace ="boardComment.Mapper";
	
	
	private static final Logger logger=LoggerFactory.getLogger(BoardCommentDAOImpl.class);
	
	
	
	@Override
	public List<BoardCommentDTO> commentList(int board_idx,  PageMaker page) {
		List<BoardCommentDTO> list=null;
		try{
			Map<String, Object> map =new HashMap<>();
			map.put("pageStart", page.getCri().getPageStart());
			map.put("perPageNum", page.getCri().getPerPageNum());
			map.put("board_idx", board_idx);
			
			list=sqlSession.selectList(namespace+".commentList", map);
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


	@Override
	public int commentCount(int board_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".commentCount",board_idx );
	}	
	
}



