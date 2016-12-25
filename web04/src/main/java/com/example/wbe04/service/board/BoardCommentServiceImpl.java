package com.example.wbe04.service.board;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.wbe04.model.board.dao.BoardCommentDAO;
import com.example.wbe04.model.board.dto.BoardCommentDTO;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {

	
	private static final Logger logger =LoggerFactory.getLogger(BoardCommentServiceImpl.class);
	
	@Inject
	private BoardCommentDAO comentDAO ;
	
	
	@Override
	public List<BoardCommentDTO> commentList(int board_idx) {
		// TODO Auto-generated method stub
		return comentDAO.commentList(board_idx);
	}

	
	
	@Override
	public void comment_insert(BoardCommentDTO commentDTO) {
		// TODO Auto-generated method stub
		comentDAO.comment_insert(commentDTO);
	}

	
	
	
	
}
