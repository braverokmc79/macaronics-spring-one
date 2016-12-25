package com.example.wbe04.service.board;

import java.util.List;

import com.example.wbe04.model.board.dto.BoardCommentDTO;

public interface BoardCommentService {

	
	public List<BoardCommentDTO> commentList(int board_idx);
	public void comment_insert(BoardCommentDTO commentDTO);
	
}
