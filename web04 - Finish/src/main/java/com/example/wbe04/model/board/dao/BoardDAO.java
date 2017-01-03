package com.example.wbe04.model.board.dao;

import java.util.List;

import com.example.wbe04.model.board.dto.BoardDTO;
import com.example.wbe04.util.mysql.Criteria;
import com.example.wbe04.util.mysql.SearchCriteria;

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

	public BoardDTO boardView(int idx);
	
	
	public void updateHit(int idx) throws Exception;
	
	
	//게시물 그룹 순서 조정 (답변) 순서번호가 들어가면 그이하 번호는 +1씩 증가
	public void reorderUpdate(int ref, int reorder);
	
	public void replyInsert(BoardDTO dto);
	
	//mysql ref 값 넣기
	
	public int lastInsertIdx();

	public void refUpdate(int ref);

	public void boardDelete(int idx);

	public int replyExist(Integer ref, Integer reorder);

	public void boardUpdate(BoardDTO dto);

	public int pageTotalCountSearch(String searchType, String keyword);

	public List<BoardDTO> listCriteraSearch(Criteria cri, String searchType, String keyword);

	List<BoardDTO> listCriteraSearch(SearchCriteria cri);
	
	
}








