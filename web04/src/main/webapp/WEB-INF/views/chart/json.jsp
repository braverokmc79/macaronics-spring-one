<%@page import="com.example.wbe04.model.shop.dto.CartDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"  %>

<%@ include file="../include/menu.jsp"  %>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
     
    google.charts.load('current', {'packages':['corechart'] }  );
    //google.charts.load('current', {'packages':['bar']}); 
    
    google.charts.setOnLoadCallback(drawChart);
      function drawChart() {

        var data = google.visualization.arrayToDataTable(
        	${str}	
        );

        var options = {
          title: '상품가격 비율'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>


<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script>

	google.load('visualization', '1', {

	'packages' : [ 'corechart' ]

		});

		google.setOnLoadCallback(drawChart2);
 // 차트 그리기 함수
	function drawChart2() {
		//json 데이터 받아로기
		// dataType : "json" 결과값이 json 형식
		// async :false  비동기식 옵션을 끔(동기식)
		// ajax 는 비동기식이다. 즉 기본값이 비동기식 true 이다
		// 즉 차트가 그려지기 전에는 다른 작업은 하지 못한다.
		//responseText  : 서버의 응답 텍스트
			var jsonData = $.ajax({
			
			url : "/chart/cart_money_list.do",
			
			dataType : "json",
			
			async : false
			
			}).responseText;
	//	alert(jsonData);
		//json 데이터를 데이터 테이블로 변환
		var data = new google.visualization.DataTable(jsonData);
		
		// 차트 그리기 (PieChart, LineChart, ColumnChart)
		var chart = new google.visualization.PieChart(document
		
			.getElementById('chart_div'));
			
			//draw(데이터, 옵션)
			chart.draw(data, {
				width : 400,
				height : 240
		});

 }


</script>



<div class="bg-content">       
  <!--============================== content =================================-->      
<div class="container" >




<h3>1. 구글 차트</h3>

  <div id="piechart" style="width: 900px; height: 500px;"></div>
   
  <div style="background-color: white;">
  	
  </div>
  
 
<h3>2. 구글  차트</h3>
 
 <div id="chart_div">
 
 </div>
 
 

<p> 
<table class="table" style="background-color: white;">
<tr>
	<td>상품명</td>
	<td>가격 </td>
</tr>

<c:forEach items="${list }" var="row">
<tr>
 <td>${row.product_name }</td>
 <td>${row.price }</td>
</tr>
</c:forEach>
</table>
  
  
  
   </div>
</div>



<%@  include file="../include/footer.jsp" %>



