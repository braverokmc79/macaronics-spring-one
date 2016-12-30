package com.example.wbe04.model.mail.dto;

import lombok.Data;

@Data
public class MailDTO {

	private String senderName;
	private String senderMail;
	private String receiveMail;
	private String subject;
	private String message;
	
}
