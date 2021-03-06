package com.example.wbe04.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.wbe04.model.member.dao.MemberDAO;
import com.example.wbe04.model.member.dto.MemberDTO;
import com.example.wbe04.util.encoder.PasswordEncoding;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	private static Logger logger =LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberDAO memberDAO;
	
	
	@Inject
	private PasswordEncoding passwordEncoding;
	

	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login2(String userid, String userpw, HttpServletRequest request, RedirectAttributes rttr){
		
		
		
		try{	
			//DB에서 가져온 패스워드
			String dbPw=memberDAO.loginPasswd(userid);
			//내가 입력한 input 패스워드 와 매치 해서 비교
			//true 일치.
			logger.info("DB에서 가져온 dbpw 값: " +dbPw );
			if(passwordEncoding.matches(userpw, dbPw)){
				
				HttpSession session =request.getSession(false);
				//다시 로그인 수정
				MemberDTO dto  =memberDAO.login(userid, dbPw);
		
				session.setAttribute("loginUser", dto);
				return "redirect:/";
			}else{
				rttr.addFlashAttribute("loginError", "아이디 또는 패스워드가 일치하지 않습니다.");	
				
				return "redirect:login";
			}
			
		}catch(Exception e){
			logger.info(e.getMessage());
			return "redirect:login";
		}
		
	}
	
	
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		logger.info("login GET 호출 ....");
		
		return "/member/login";
	}
	
	
	
	@RequestMapping(value="/login2", method=RequestMethod.POST)
	public String login(String userid, String userpw, HttpServletRequest request, RedirectAttributes rttr){
		logger.info("login 호출  POST....");
		MemberDTO dto =null;
		logger.info( " user id : " + userid + "  user pw :  " +  userpw );
		try{				
			dto =memberDAO.login(userid, userpw);
			if(dto!=null){
				
				HttpSession session =request.getSession(false);
				session.setAttribute("loginUser", dto);
				return "redirect:/";
			
			}else{
				rttr.addFlashAttribute("loginError", "아이디 또는 패스워드가 일치하지 않습니다.");	
				
				return "redirect:login";
			}
			
		}catch(Exception e){
			logger.info(e.getMessage());
			return "redirect:login";
		}
		
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rttr){
		logger.info("login out 호출 ....");
		
		
		session.removeAttribute("loginUser");
		session.invalidate();
		
		rttr.addFlashAttribute("logOutMessage", "로그 아웃 되었습니다.");
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value="/member_info.do", method =RequestMethod.GET)
	public String member_info(HttpSession session){
		
		MemberDTO   member=(MemberDTO)  session.getAttribute("loginUser");
		if(member!=null){
			
			// 정보 업데이트 유저를 위해 시 DB에서 정보를 가져와서 셋팅 한다.
			member=memberDAO.loginInfo(member.getUserid());
			
			session.removeAttribute("loginUser");
			session.setAttribute("loginUser", member);
			
			return "/member/member_info";
		}else{
			
			return "redirect:/";
		}
	}
	
	
	@RequestMapping(value="/jusoPopup")
	public String jusoPopup(){
		logger.info("jusoPopup GET 호출 ....");
		
		return "/member/jusoPopup";
	}	


	@RequestMapping(value="/memebrUpate.do" , method=RequestMethod.POST)
	public String memebrUpdate(@ModelAttribute MemberDTO memberDTO , RedirectAttributes rttr){
		
		//패스워드 암호화
		String userpw =passwordEncoding.encode(memberDTO.getUserpw());
		memberDTO.setUserpw(userpw);
		
		memberDAO.memberUpdate(memberDTO);
		
		
		rttr.addFlashAttribute("message", "정보가 변경 되었습니다.");

		return "redirect:member_info.do";
	}
	
	
	
	
}


