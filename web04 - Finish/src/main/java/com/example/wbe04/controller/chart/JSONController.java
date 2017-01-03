package com.example.wbe04.controller.chart;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.wbe04.model.chart.dto.ChartProductPriceDTO;
import com.example.wbe04.service.chart.ChartProductPriceService;

@Controller
@RequestMapping(value="/chart")
public class JSONController {

	@Inject
	private ChartProductPriceService chartProductPriceService;
	
	@RequestMapping(value="/json.do", method=RequestMethod.GET)
	public ModelAndView json_get(){
			
		ModelAndView modelAndView=new ModelAndView();
		
		
		List<ChartProductPriceDTO>  list=  chartProductPriceService.chartProductList();
		modelAndView.addObject("list", list);
		modelAndView.setViewName("/chart/json");;
		
		System.out.println(" 리스트 사이즈  : " + list.size());
		
		String str ="[";
		str +="['상품명' , '가격'] ,";
		int num =0;
		for(ChartProductPriceDTO  dto : list){
			
			str +="['";
			str  += dto.getProduct_name();
			str +="' , ";
			str += dto.getPrice();
			str +=" ]";
			
			num ++;
			if(num<list.size()){
				str +=",";
			}		
		}
		str += "]";
		modelAndView.addObject("str", str);
		return modelAndView;
				
	}
	
	
/*	 구글 차트 JSON 데이터의 형식
	{
	    "cols": [
	        {"label":"Topping","type":"string"},
	        {"label":"Slices","type":"number"}
	    ],        
	    "rows": [
	        {"c":[{"v":"Mushrooms"},{"v":3}]},
	        {"c":[{"v":"Onions"},{"v":1}]},
	        {"c":[{"v":"Olives"},{"v":1}]},
	        {"c":[{"v":"Zucchini"},{"v":1}]},
	        {"c":[{"v":"Pepperoni"},{"v":2}]}
	    ]
	}
	*/
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/cart_money_list.do" ,method=RequestMethod.GET)
	public ResponseEntity<JSONObject> money_list(){
		ResponseEntity<JSONObject>  entity=null;
		List<ChartProductPriceDTO>  items=  chartProductPriceService.chartProductList();
		//리스트 형태를 json 형태로 만들어서 리턴
		JSONObject data =new JSONObject();
		
		//컬럼객체
		JSONObject col1 =new JSONObject();
		JSONObject col2 =new JSONObject();
		JSONArray title =new JSONArray();
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type" , "number");
		
		title.add(col1);
		title.add(col2);
		
		data.put("cols", title);
/*		
		"rows": [
			        {"c":[{"v":"Mushrooms"},{"v":3}]},
			        {"c":[{"v":"Onions"},{"v":1}]},
			       ]
			       
		rows : [ 배열 (객체 :배열[객체])]
		
*/ 	

		//들어갈 형태  ->  rows 객체 에 배열  <- 
		//  <- [  c 라는 객체에 배열 <- 객체
		//  data 객체 -> rows 배열 <-  c 객체  ->배열  <- v 객체 2개/
	
		JSONArray  body =new JSONArray();
		for(ChartProductPriceDTO  dto : items){
			JSONObject name =new JSONObject();
			name.put("v", dto.getProduct_name()); //상품이름 -> v 객체 
			JSONObject price =new JSONObject(); 
			price.put("v", dto.getPrice()); //가격 ->v 객체

			//  v객체를 row 배열을 만든후 추가한다.
			JSONArray row =new JSONArray();
			row.add(name);
			row.add(price);   
 
			//   c 객체 를 만든후 row 배열을 담는다.
			JSONObject c =new JSONObject();
			c.put("c", row);		
			// c 객체를 배열 형태의 body 에 담는다.
			body.add(c);		
		}
		// 배열 형태의 body 를 rows 키값으로 객체 data 에 담는다.
		data.put("rows", body);
		try{
			 entity =new ResponseEntity<JSONObject>(data, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(" 에러            -- ");
			entity =new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	
	
	
	
}





