package com.example.wbe04.page;

import lombok.Data;

// Oracle  페이지 


public class PageDAO {

	public static final int PAGE_SCALE  =10;   //페이지 개당 게시물
	public static final int BLOCK_SCALE =10;  // 블록당 페이지 수
	private int curPage;  //현재 페이지 번호
	private int prevPage; //이전 페이지
	private int nextPage; //다음 페이지
	private int totPage; //전체 페이지 갯수
	private int curBlock; // 현재 페이지 블록 번호
	private int totBlock; // 전체 페이지 블록 갯수
	private int pageBegin; //페이지 내에서 레코드 시작 번호
	private int pageEnd; //페이지 내에서 레코드 끝 번호
	private int blockStart; //페이지 블록 내에서의 시작 페이지 번호 1 2 3 
	private int blockEnd; // 페이지 블록 내에서의 마지막 페이지 번호
	
	
	
	public PageDAO(int count, int curPage){

		curBlock =1; // 현재 페이지 블록은 1로 설정
		this.curPage =curPage; // 현재 페이지 번호 설정
		setTotPage(count); // 전체 페이지 갯수 설정
		setPageRange(); //현재 페이지 시작번호 , 끝번호 계산
		setTotBlock(); //전체 페이지 블록 계산
		setBlockRange(); //현재 페이지 블록의 시작 페이지, 끝 페이지 번호 계산
	}

	
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotPage() {
		return totPage;
	}
	
	
	//전체 페이지 갯수 계산  count 는 레코드 갯수 567 개
	public void setTotPage(int count) {
	//Math.ceil(숫자) =>소수 올림 처리, 5.1 =>6	
   // 567 /10 => 56.7		
		totPage=(int)Math.ceil(count*1.0/PAGE_SCALE);
	}
	
	
	
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getTotBlock() {
		return totBlock;
	}
	public void setTotBlock(int totBlock) {
		this.totBlock = totBlock;
	}
	public int getPageBegin() {
		return pageBegin;
	}
	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getBlockStart() {
		return blockStart;
	}
	public void setBlockStart(int blockStart) {
		this.blockStart = blockStart;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	//전체 페이지 블록 계산
	/* 1 ~ 5 |   6 ~ 10  | 11 ~ 15 | ..... | 56 ~ 57
	   1블록 	 2블록 		3블록 				12블록
	
	페이지 블록 갯수 : 57페이지 / 5페이지 블록 => 11.4 =>12 개 블록
	*/
	
	private void setTotBlock() {
		totBlock =(int)Math.ceil(totPage/BLOCK_SCALE);
	}
	
	//현재 페이지 시작번호 , 끝번호 계산  오라클  where  rn BETWEEN 1(pageBegin) and 10(pageEnd);
	private void setPageRange() {
		pageBegin =(curPage -1) * PAGE_SCALE +1;
		pageEnd =pageBegin + PAGE_SCALE -1;
		
	}

	//현재 페이지 블록의 시작 페이지, 끝 페이지 번호 계산
	private void setBlockRange() {
      //현재 페이지가 몇번째 페이지 블록에 속하는지 계산	
	   curBlock=(int)Math.ceil((curPage-1)/BLOCK_SCALE)+1;
	   
	   blockStart=(curBlock -1) * BLOCK_SCALE+1; //시작 번호
	   blockEnd=blockStart+BLOCK_SCALE -1; // 끝번호
	   if(blockEnd > totPage){  // 마지막 페이지가 범위를 초과할 경우
		  //[이전 ] 56 57 58 59  60  => 57 이후 날려 버린다.
		  // => [이전 ] 56 57  
		   blockEnd=totPage;
	   }
	   //현재 블록이 1이면 이전 페이지를 1로 설정
	   prevPage= curBlock ==1 ? 1 : (curBlock-1)*BLOCK_SCALE;   // (5-1) *12 =48page
	   //현재 블록이 마지막 블록이면 다음 페이지를 마지막 페이지 번호로 설정
	   nextPage= curBlock > totBlock ? (curBlock*BLOCK_SCALE) :(curBlock * BLOCK_SCALE) +1;
	   //마지막 페이지가 범위를 초과하지 않도록 처리
	   if(nextPage >= totPage){
		   nextPage =totPage;
	   }
	}
	
	
	
	
}
