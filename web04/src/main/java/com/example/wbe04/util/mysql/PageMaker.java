package com.example.wbe04.util.mysql;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int tempEndPage; //마지막 페이지
	
	
	private int displayPageNum =10;
	
	private Criteria cri;
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}
	
	
	private void calcData(){
		
			endPage =(int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
			
			startPage =(endPage - displayPageNum) +1;
			
			tempEndPage =(int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
			if(endPage >tempEndPage){
				endPage=tempEndPage;
			}
			
			prev =startPage ==1 ? false :true;
			next =endPage *cri.getPerPageNum() >=totalCount ? false :true;
	}
	
	
	public String makeQuery(int page){
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		return uriComponents.toUriString();
	}
	
	
}







