package com.example.wbe04.model.board.dao;

import java.util.List;

import com.example.wbe04.model.board.dto.BoardCommentDTO;
import com.example.wbe04.util.mysql.PageMaker;

public interface BoardCommentDAO {

	public List<BoardCommentDTO> commentList(int board_idx, PageMaker page);
	public void comment_insert(BoardCommentDTO commentDTO);
	public int commentCount(int board_idx);
	
}
