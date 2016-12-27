package com.example.wbe04.service.board;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@Override
	public void boardInsert(BoardDTO dto) {
		// TODO Auto-generated method stub
		try {	
			boardDAO.boardInsert(dto);
			//현재 삽입한 idx 번호 가져오기
			int idx=boardDAO.lastInsertIdx();
			//ref 를 업데이트 하기
			logger.info(" idx ***************************" + idx);
			boardDAO.refUpdate(idx);
			
		} catch (Exception e) {
			
			return ;
		}
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


	@Override
	public BoardDTO boardView(int idx) {
		// TODO Auto-generated method stub
		try{
			//조회수 증가
			boardDAO.updateHit(idx);
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return boardDAO.boardView(idx);
	}

	
	
	//답변 달린 게시글 그룹 순서 정렬 하기
	@Override
	public void reorderUpdate(int ref, int reorder) {
		
		boardDAO.reorderUpdate(ref, reorder);
	}


	@Override
	public void replyInsert(BoardDTO dto) {
		
		boardDAO.replyInsert(dto);
	}

	@Override
	public void boardDelete(int idx) {
		
		boardDAO.boardDelete(idx);
	}

	@Override
	public int replyExist(Integer ref, Integer reorder) {
		// TODO Auto-generated method stub
		return boardDAO.replyExist(ref, reorder);
	}
	
	
	
	
}







