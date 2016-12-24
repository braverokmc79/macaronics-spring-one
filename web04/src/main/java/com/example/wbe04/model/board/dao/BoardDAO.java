package com.example.wbe04.model.board.dao;

import java.util.List;

import com.example.wbe04.model.board.dto.BoardDTO;
import com.example.wbe04.util.mysql.Criteria;

public interface BoardDAO {

	//오라클
	public List<BoardDTO> boardList(int start, int end);
	
	public void boardInsert(BoardDTO dto);
	
	public void downCont(int idx);

	
	public int pageTotalCount();
	
	
	
   
	//Mysql 
	public List<BoardDTO> listPage(int page) throws Exception;
	
	//MYSQL
	public List<BoardDTO> listCritera(Criteria cri) throws Exception;
	
}








