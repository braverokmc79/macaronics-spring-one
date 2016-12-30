package com.example.wbe04.service.mail;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.wbe04.model.mail.dto.MailDTO;

@Service
public class MailService {

	@Inject
	JavaMailSender mailSender;
	
	public void sendMail(MailDTO dto){
	    try {
			MimeMessage msg =mailSender.createMimeMessage();
			//javax.mail.internet.MimeMessage.RecipientType;
			//이메일 수신자
			msg.addRecipient(RecipientType.TO,
					new InternetAddress(dto.getReceiveMail()));
			
			//이메일 발신자
			msg.addFrom(new InternetAddress[]{
					new InternetAddress(dto.getSenderMail(), dto.getSenderName())
			});
			//제목
			msg.setSubject(dto.getSubject(), "utf-8");
			//본문
			msg.setText(dto.getMessage(), "utf-8");
			//메일 발송
			mailSender.send(msg);
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

	
	
}






