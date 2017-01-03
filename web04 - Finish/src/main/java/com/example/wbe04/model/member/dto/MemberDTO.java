package com.example.wbe04.model.member.dto;

import java.sql.Date;

import com.example.wbe04.util.FormTagReplace;

import lombok.Data;

@Data
public class MemberDTO {

	private String userid;
	private String userpw;
	private String username;
	private String email;
	
	private String tel;

	private String zipcode;
	private String address1;
	private String address2;
	
	private Date regdate;
	private Date updatedate;
	
	
	
	
}


