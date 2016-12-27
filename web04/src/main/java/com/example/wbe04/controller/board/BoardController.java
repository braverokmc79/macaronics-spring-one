package com.example.wbe04.controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.wbe04.model.board.dto.BoardDTO;
import com.example.wbe04.model.member.dto.MemberDTO;
import com.example.wbe04.page.PageDAO;
import com.example.wbe04.service.board.BoardService;
import com.example.wbe04.util.MediaUtils;
import com.example.wbe04.util.UploadFileUtils;
import com.example.wbe04.util.UploadPath;
import com.example.wbe04.util.mysql.Criteria;
import com.example.wbe04.util.mysql.PageMaker;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService  boardService;
	
	private final Logger logger =LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public String board_list(@RequestParam(defaultValue="1") int curPage,  Model model){
		
		//페이지 총 개수 가져오기			
		int count =boardService.pageTotalCount();
		
		PageDAO  pageDao =new PageDAO(count, curPage);
		
		int start =pageDao.getPageBegin();
		int end =pageDao.getPageEnd();
		
		model.addAttribute("list", boardService.boardList(start, end));
		model.addAttribute("page", pageDao);
		
		return "board/board_list";
	}
	
	
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String write(){
		
		return  "/board/write";
	}
	
	
	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardDTO dto , HttpServletRequest request, Model model) {
		
		//입력 값이 없을 경우
		if(dto.getSubject().trim().length() ==0){
			logger.info("입력 값이 없을 경우");
			
			return "board/write";
		}else{
			
			//입력 값이 있을 경우
			logger.info("입력 값이 있을 경우" +dto.toString());
			String fileName ="";
			long fileSize=0;
			MultipartFile file1 =dto.getFile1();
			
			if(!file1.isEmpty()){
				logger.info("첨부 파일이 존재 한다.");
				fileName=file1.getOriginalFilename();
				fileSize=file1.getSize();
				UploadPath.attach_path="WEB-INF/uploads";
				String uploadDir =UploadPath.path(request);
				try {
					logger.info("업로드 ");
					fileName=UploadFileUtils.uploadFile(uploadDir, fileName, file1.getBytes());
					
					dto.setFileName(fileName);
					dto.setFilesize(fileSize);
						
					boardService.boardInsert(dto);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.getStackTrace();
					logger.info("업로드 오류");
				}	
			}else{
				boardService.boardInsert(dto);
			}
			//list.do 로 이동
			return "redirect:listPage";
		}
	}
	
	
	@RequestMapping("/down.do")
	public void fileName(@RequestParam Integer idx, @RequestParam String fileName,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		UploadPath.attach_path="WEB-INF//uploads";
		String uploadDir =UploadPath.path(request);
		logger.info(" getParameter : " + fileName);
		
		//이미지 일 경우 원본이미지로 주소 변경
		String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		MediaType mType=MediaUtils.getMediaType(formatName);
		if(mType!=null){
			//원본 이미지 다운로드
			String front=fileName.substring(0, 12);
			String end=fileName.substring(14);
			fileName=front+ end;
		}
		logger.info(" 다운로드할  파일 이름" + fileName);
		logger.info(" 다운로드 할  파일 주소  + 이름 " + uploadDir+fileName.replace('/', File.separatorChar));
		//다운로드 시작
		//스트림을 이용하여 서버의 파일을 클라이언트에게 전송
		File file =new File(uploadDir+fileName.replace('/', File.separatorChar));
		
		
		//파일 의 header정보
		response.setContentType("application/octet-strem");
		response.setContentLength((int)file.length());
		
		//파일이름은 인코딩를 해야 함(한글 처리)
		//"_원본이름.jpg 마지막 슬러시 기준으로 원본이름 추출
		String hangule =fileName.substring(fileName.lastIndexOf("_")+1);
		logger.info( " hangule :"  +hangule);;
		response.setHeader("Content-Disposition", 
				"attachment; filename=\""+new String(hangule.getBytes("UTF-8"), "ISO-8859-1") +"\"");

		//binary 전송합니다
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// 실제 첨부파일 전송
		OutputStream out =response.getOutputStream();
		FileInputStream fis =null;
		try{
			fis =new FileInputStream(file);
			
			//서버의 파일을 클라이언트로 전송
			FileCopyUtils.copy(fis, out);
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try{
					fis.close();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		}
		out.flush(); //버퍼 비우기(다운로드 완료)
	
		try{
			logger.info("//다운로드 횟수 증가");
			//다운로드 횟수 증가
			boardService.downCont(idx);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("다운로드 증가 오류");
		}
	}
	
	
	//MYSQL
	@RequestMapping(value="/listCri", method=RequestMethod.GET)
	public String listAll(Criteria cri, Model model) throws Exception{
		
		
		model.addAttribute("list", boardService.listCriteria(cri));
		
		
		//model.addAttribute("page", "");
		
		return "/board/mysql_list";
	}
	
	
	//MYSQL
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception{
		
		model.addAttribute("list", boardService.listCriteria(cri));
		PageMaker pageMaker =new PageMaker();
		pageMaker.setCri(cri);
		
		//페이지 총 개수 가져오기			
		int count =boardService.pageTotalCount();
		
		pageMaker.setTotalCount(count);
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	
	@RequestMapping(value="/view.do", method=RequestMethod.GET)
	public String viewDo(@RequestParam("idx") int idx, Model model){
		
		
		//board_v idx 값을 가져온다.
		
		model.addAttribute("view", boardService.boardView(idx));
		
		return  "/board/view";
	}
	
	
	
	//답변 페이지로 이동 
	
	@RequestMapping(value="/board_reply.do", method=RequestMethod.POST)
	public String borad_reply(@RequestParam Integer idx, Model model){
		
		BoardDTO dto =boardService.boardView(idx);
		String content ="<h3 style='color:red; font-style:blod;'>================게시물 내용 =================</h3>\n";
		
		
		dto.setContent(content+dto.getContent());
		
		model.addAttribute("dto", dto);
		
		return "board/board_reply";
	}

	
	
	
	//답변 달기
	
	@RequestMapping(value="/reply_insert.do", method=RequestMethod.POST)
	public String reply_insert(@RequestParam  Integer idx, @RequestParam String subject,
			  @RequestParam String content1,  @RequestParam String content2,
			HttpSession session){
		
		BoardDTO dto =boardService.boardView(idx);
		
		logger.info("뷰에서 넘겨 온 값 :" + dto.toString());
		
		
		
		//******************* 첫번째 reorder 증가처리 ********************* 
		int ref =dto.getRef();
		int depth=dto.getDepth() +1; //답변 단계
		int reorder =dto.getReorder() +1; //같은 그룹내에서 순서
		
		
		
		// DB에서 다시 ******************* 두번째 reorder  증가처리 *********************
		//게시물 내에서의 순서 조정 
		//(답변글) 현재 reorder 번호보다 큰것들은 1씩 증가로 셋팅 한다.
		boardService.reorderUpdate(ref, reorder);
		logger.info("리오더 1씩 증가 완료 :");
		
		
		//답변 게시글 테이블에 저장
		MemberDTO loginUser=(MemberDTO) session.getAttribute("loginUser"); 
		dto =new BoardDTO();
		dto.setRef(ref);
		dto.setUserid(loginUser.getUserid()); //댓글 등록할 userid
		dto.setUsername(loginUser.getUsername());//댓글 등록할 username
		
		dto.setSubject(subject); // 파라미터로 넘겨온 subject
		dto.setContent(content1 +content2); //원본 게시글  + 답변 게시글	
		dto.setDepth(depth);
		dto.setReorder(reorder);
		boardService.replyInsert(dto);
		return "redirect:listPage";
	}
	
	
	
	//게시물 삭제 처리
	@RequestMapping(value="/board_delete.do", method=RequestMethod.POST)
	public String board_delete(@RequestParam int idx, @RequestParam Integer ref,  
			@RequestParam Integer reorder, RedirectAttributes rttr){

			//답변이 있는 게시물, 댓글이 있는 게시물에 대한 처리가 필요함
			//만약 0이면 답변이 없다
			//select count(*) from board where ref=3 and reorder >4;
		   
			if(boardService.replyExist(ref, reorder) == 0 ){
				//답변이 없다. 게시물 삭제
				boardService.boardDelete(idx);
				//게시물 목록 이동
				return "redirect:listPage";
			}else{
				
				rttr.addFlashAttribute("replyMsg", "답변이 달린 게시글은 삭제 할 수 없습니다.");
				return "redirect:view.do?idx="+idx;
			}
	}
	
	
	
	

	
	
}







