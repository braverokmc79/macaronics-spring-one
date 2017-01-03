package com.example.wbe04.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbe04.model.board.dto.BoardCommentDTO;
import com.example.wbe04.page.PageDAO;
import com.example.wbe04.service.board.BoardCommentService;
import com.example.wbe04.util.mysql.Criteria;
import com.example.wbe04.util.mysql.PageMaker;

@RestController //spring4.0 부터 가능
@RequestMapping(value="/board")
public class CommentController {

	@Inject
	private BoardCommentService service;
	
	private final Logger logger =LoggerFactory.getLogger(CommentController.class);
	
	//댓글 등로 Ajax 로 처리
	
	@RequestMapping(value="/comment_insert.do", method=RequestMethod.POST)
	public ResponseEntity<String> comment(HttpSession session, @RequestBody BoardCommentDTO comment){
		
		
		ResponseEntity<String> entity=null;
		
		try{
			logger.info("가져온 값" +comment.toString());
			service.comment_insert(comment);
			
			entity=new ResponseEntity<>("SUCCESS",  HttpStatus.OK);
		}catch(Exception e){
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	

	/*//1. @PathVariable 방식 
	@RequestMapping(value="/comment_list.do/{board_idx}/{page}", method=RequestMethod.GET)
	public ResponseEntity<List<BoardCommentDTO>> comment_list(@PathVariable("board_idx") int board_idx, 
			@PathVariable("page") Integer page){
		

		ResponseEntity<List<BoardCommentDTO>> entity=null;
		try {
			
		     //오라클PageDAO dao =new PageDAO(count, curPage) 
			
			Criteria cri =new Criteria();
			cri.setPage(page);
			
			PageMaker maker =new PageMaker();
			maker.setCri(cri);
			maker.setTotalCount(service.commentCount(board_idx));
			
			
			
			List<BoardCommentDTO> commentList=service.commentList(board_idx, maker);
			logger.info(commentList.toString());
			entity =new ResponseEntity<List<BoardCommentDTO>>(commentList  ,HttpStatus.OK);
		} catch (Exception e) {
			entity =new ResponseEntity<List<BoardCommentDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}*/
	
	
	
	
	
	//2. @RequestParam 방식 
	@RequestMapping(value="/comment_list.do", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Map<String, Object>> comment_list2(@RequestParam Integer curpage

			 , @RequestParam int board_idx){
		

		ResponseEntity<Map<String, Object>> entity=null;
		try {
			
			/* sql ex) select comment_count from board_v where idx =762*/
			
			int count =service.commentCount(board_idx);
			
			Criteria cri =new Criteria();
			cri.setPerPageNum(5);
			cri.setPage(curpage);
			
			PageMaker page =new PageMaker();
			page.setCri(cri);
			page.setTotalCount(count);
			
			
			List<BoardCommentDTO> commentList=service.commentList(board_idx, page);
			logger.info("카운트 갯수  :" + count + "  page : "+ page.toString());
			
			
			
			Map<String, Object> map =new HashMap<>();
			map.put("pageMaker", page);
			map.put("commentList", commentList);
			
			
			entity =new ResponseEntity<Map<String, Object>>(map  ,HttpStatus.OK);
		} catch (Exception e) {
			entity =new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	//3. REST  방식 아니라 모델로 직접 데이터를 넘겨주는 방식 
	@RequestMapping(value="/comment_list3.do", method=RequestMethod.GET)
	public List<BoardCommentDTO> comment_list3(PageMaker page ,@RequestParam int board_idx
			, Model model){
		

		List<BoardCommentDTO> commentList=null;
		try {
			
			 commentList=service.commentList(board_idx, page);
			logger.info(commentList.toString());
			
		} catch (Exception e) {
			
		}
		
		return commentList;
	}
	
	
	
	
	
}
