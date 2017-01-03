package com.example.wbe04.controller.mail;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.wbe04.model.mail.dto.MailDTO;
import com.example.wbe04.service.mail.MailService;

@Controller
@RequestMapping("/mail")
public class GoogleMailController {

	@Inject
	MailService mailService;
	
	
	
	@RequestMapping(value="/mail_form.do" , method=RequestMethod.GET)
	public String mail_form(){
		
		return "/mail/write";
	}
	
	
	@RequestMapping(value="/mail_sender.do", method=RequestMethod.POST)
	public String mail_sender(@ModelAttribute MailDTO mail, Model model){
		
		try {
			mailService.sendMail(mail);
			model.addAttribute("message", "메일을 발송 하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:mail_form.do";
	}
	
	
}



