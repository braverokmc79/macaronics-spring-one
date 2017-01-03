package com.example.wbe04.model.board.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardCommentDTO {
	private int comment_idx;
	private int board_idx;
	private String userid;
	private String content;
	private Date post_date;
}
