package com.example.wbe04.model.board.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	
	private int idx;
	private String userid;
	private String username;
	private String subject;
	private String content;
	private int hit;
	private Date post_date;
	private String fileName;
	private long filesize;
	private int down;
	private int ref; 
	private int depth ;
	private int reorder;
	private int comment_count;
	
	private String fileCheck;
	private String oldFileName;
	private long oldFileSize;
	
	
	private MultipartFile file1;
	

}