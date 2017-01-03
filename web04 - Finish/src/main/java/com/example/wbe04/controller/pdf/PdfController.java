package com.example.wbe04.controller.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.Phaser;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.wbe04.model.board.dto.BoardDTO;
import com.example.wbe04.service.board.BoardService;
import com.example.wbe04.util.mysql.Criteria;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping(value="/pdf")
public class PdfController {

	@Inject
	BoardService boardService;
	
	
	@RequestMapping(value="/pdf_save.do")
	public String pdfSave(HttpServletRequest request, Model rttr) {
		
		try{
			// 패키지 설정 주의 - import com.itextpdf.text.Document;
			//pdf  document 객체 만들기
			Document document=new Document();
			//pdfWriter  객체 만들기
			//PdfWriter.getInstance(도큐먼트 객체, OutputStream(pdf 파일 경로))
			PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\cyj\\Desktop\\fileup-master\\sample2.pdf"));
			
			//pdf docuemnt 열기
			document.open();
			
			//한글 처리를 위해 폰트 설정
			//무료 포튼 다운로드 http://www.tryneat.net/
			String path =request.getSession().getServletContext().getRealPath("/");
			BaseFont baseFont=
					BaseFont.createFont(path +"/resources/template/font/HoonJunglebook.ttf".replace('/', File.separatorChar), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// com.itextpdf.text.Font; 패키지
			Font font =new Font(baseFont, 12); // 폰트 , 폰트 사이즈
			
			
			
			//테이블 객체
			PdfPTable table =new PdfPTable(4);// 4 column
			Chunk   chunk =new Chunk("자유 게시판", font); //출력할 내용 긴내용도 들어간다.
			Paragraph ph =new Paragraph(chunk);
			ph.setAlignment(Element.ALIGN_CENTER);//가운데 정렬
			
			
			//pdf 문서에 내용 추가
			document.add(ph);
			
			
			//줄바꿈
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			
			
			//표의 타이틀 행 출력
			PdfPCell cell1=new PdfPCell(new Phrase("번호", font));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell1);
			
			PdfPCell cell2=new PdfPCell(new Phrase("이름", font));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell2);
			
			PdfPCell cell3=new PdfPCell(new Phrase("제목", font));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell3);
			
			
			PdfPCell cell4=new PdfPCell(new Phrase("날짜", font));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell4);
			
			
			Criteria cri =new Criteria();
			cri.setPage(1);
			cri.setPerPageNum(10);
			List<BoardDTO> items =boardService.listCriteria(cri);
			
			for(int i=1; i<items.size(); i++){
				
				BoardDTO  dto =items.get(i); // i번째 레코드 dto 에 저장
				table.addCell("" + dto.getIdx()); // 테이블에 셀을 추가  
				//addCell 이 String 밖에 안된다
				//Phrase 에서 font 를 넣지 않으면 한글이 깨진다.
				PdfPCell cellName=new PdfPCell(new Phrase(dto.getUsername(), font));
				table.addCell(cellName);
				
				PdfPCell cellsubject=new PdfPCell(new Phrase(dto.getSubject(), font));
				table.addCell(cellsubject);
				
				table.addCell("" + dto.getPost_date());
				
			}
			
			document.add(table);
			
			//pdf 문서 닫기(지금까지 작업한 내용들이 저장됨)
			document.close();
			
		}catch(Exception e){
			
			rttr.addAttribute("message", e.getMessage());
		
		}
		
		return "/pdf/result";
	}

	
	
}



