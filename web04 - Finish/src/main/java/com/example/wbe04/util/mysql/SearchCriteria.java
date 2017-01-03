package com.example.wbe04.util.mysql;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria{

	private String searchType;
	private String keyword;
	
	
}
