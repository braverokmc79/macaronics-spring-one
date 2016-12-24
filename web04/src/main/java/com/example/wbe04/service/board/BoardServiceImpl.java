package com.example.wbe04.service.board;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.wbe04.model.board.dao.BoardDAO;
import com.example.wbe04.model.board.dto.BoardDTO;
import com.example.wbe04.util.mysql.Criteria;

@Service
public class BoardServiceImpl implements BoardService {

	
	@Inject
	private BoardDAO boardDAO;
	
	
	private final static Logger logger =LoggerFactory.getLogger(BoardServiceImpl.class);
	
	
	@Override
	public List<BoardDTO> boardList(int start, int end) {
		// TODO Auto-generated method stub
		return boardDAO.boardList(start,  end);
	}


	@Override
	public void boardInsert(BoardDTO dto) {
		// TODO Auto-generated method stub
		boardDAO.boardInsert(dto);
	}


	@Override
	public void downCont(int idx) {
		boardDAO.downCont(idx);	
	}


	@Override
	public int pageTotalCount() {
	
		return boardDAO.pageTotalCount();
	}


	//MYSQL
	@Override
	public List<BoardDTO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.listCritera(cri);
	}

	
	
	
	
}







