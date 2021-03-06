package com.example.wbe04.service.board;

import java.util.List;

import com.example.wbe04.model.board.dto.BoardDTO;
import com.example.wbe04.util.mysql.Criteria;
import com.example.wbe04.util.mysql.SearchCriteria;

public interface BoardService {

	public List<BoardDTO> boardList(int start, int end);

	public void boardInsert(BoardDTO dto);

	public void downCont(int idx);


	//MYSQL
	public List<BoardDTO> listCriteria(Criteria cri) throws Exception;

	public BoardDTO boardView(int idx);
	
	
	public void reorderUpdate(int ref, int reorder);

	public void replyInsert(BoardDTO dto);

	public void boardDelete(int idx);

	public int replyExist(Integer ref, Integer reorder);

	public void boardUpdate(BoardDTO dto);

	
	public int pageTotalCount();
	
	public int pageTotalCountSearch(String searchType, String keyword);

	public Object listCriteraSearch(Criteria cri, String searchType, String keyword);

	public Object listCriteraSearch(SearchCriteria cri);

	public int pageTotalCountSearch(SearchCriteria cri);
	
	
	
}
