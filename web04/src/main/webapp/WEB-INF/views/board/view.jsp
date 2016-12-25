<%@page import="com.example.wbe04.model.shop.dto.CartDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"  %>

<%@ include file="../include/menu.jsp"  %>

<%-- 
<%@ include file="../include/sessionCheck.jsp"  %> 
--%>

<!--   ckeditor 연결  -->
<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>



<div class="bg-content">       
  <!--============================== content =================================-->      

<div class="container" >

<h3>게시판 글쓰기 </h3>



<form name="from1" role="form1" method="post" enctype="multipart/form-data" action="/board/write.do">
<div class="table-responsive">

<input type="text" name="userid" value="${loginUser.userid }" />
<input type="text" name="username" value="${loginUser.username }" />

<table class="table table-bordered table-striped" style="background-color: white; margin: 10px 0 10px 0;">
<colgroup>

<col class="col-lg-1">
<col class="col-lg-7"></colgroup>

<thead>
<tr class="alt">
<td><code>항목</code> </td>
<td><code>클래스</code> </td>

</tr>
</thead>
<tbody id="divList" style="margin-bottom: 30px;">

<tr>
<td>번호</td>
<td>${view.idx}</td>
</tr>



<tr>
<td>글쓴이</td>
<td>${view.username}</td>
</tr>


<tr>
<td>제목</td>
<td class="span12">

${view.subject }
</td>
</tr>



<tr>
<td>내용</td>
<td class="span12">
${view.content }

</td>



<tr>
<td>날짜</td>
<td>${view.post_date}</td>
</tr>



<tr>
<td>조회수</td>
<td>${view.hit}</td>
</tr>



<tr>
<td>파일</td>
<td>

	<c:if test="${ not empty view.fileName }">
	<a href="/board/down.do?idx=${view.idx }&fileName=${view.fileName }"> <img src="/resources/template/img/file.png"/></a>
	</c:if>
	

</td>
</tr>















</tbody>


<tfoot>
<tr>
<td colspan="2" style="text-align: center;">

<a href="/board/listPage" class="btn btn-inf">목록 보기</a>
</td>
</tr>

</tfoot>

</table>
</div>
		
</form>




<hr>
<h3>댓글 등록 <c:if test="${loginUser.username !=null}" ><small style="float:right;"> 로그인 유저 : ${loginUser.username }</small></c:if></h3>


<c:if test="${loginUser.username !=null }" >
<div>
<table class="table">
<tr>
	<td >
	<textarea class="input-xxlarge" name="commnet_content" rows="5" cols="200" id="comment_content"></textarea>
	</td>
</tr>

<tr>
	<td >
		<input type="button" value="댓글 달기" class="form-controll" id="btnSave">
	</td>
</tr>
</table>
</div>

</c:if>




<hr>

<h3>댓글 목록</h3>

<div id="LplyUL" style="margin-bottom: 20px;">


</div>



</div>
</div>








<script type="text/javascript">

$(document).ready(function(){
	
	comment_list(762);
	
	
	$("#btnSave").click(function(){
		
		var comment_content =$("#comment_content").val();
		var board_idx ="${view.idx}";
		var userid="${loginUser.userid}";
		//alert(comment_content);
		$.ajax({		
			url : "/board/comment_insert.do",
			type:"post",
		/* 	headers:{
				"Content-Type" :"application/json",
				"X-HTTP-Method-Override" :"POST"
			},
			dataType:"text", 
			
				또는 => contentType:"application/json", 한줄 코딩
			*/
			contentType:"application/json",
			
			data:JSON.stringify({
				board_idx :board_idx,
				userid : userid,
				content :comment_content
				
			}),
			
			success:function(result){
				
				if(result=="SUCCESS"){
					
					$("#comment_content").val("");
					comment_list(board_idx);
				}else{
					alert("등록에 실패 하였습니다.");
				}
			}
			
		});	
		
	});
	
});




function comment_list(board_idx){
	
/* 	$.getJSON("/board/comment_list.do/"+board_idx, function(data){
		
		printData(result, $("#LplyUL"), $("#template"));
		
	});	
	
	또는
	
	
	 */
	
	
	$.ajax({		
		url : "/board/comment_list.do/"+board_idx,
		type:"GET",
		contentType:"application/json",	
		success:function(result){
			
			printData(result, $("#LplyUL"), $("#template"));
			
		}
	});	

	
	
}

var printData =function (replyArr, targetDiv, handleBarTemplateName){
	
	var template =Handlebars.compile(handleBarTemplateName.html());
	
	var html =template(replyArr);
	targetDiv.html(html);
}



/* $.getJSON("/board/comment_list.do/"+board_idx+, function(data){
	
	$(data).each(
		function(){
			str +="<tr><td>"
			str +=""+this.content;
			str +=this.post_data;
			str +="</td><tr>"
	});
	
	$("#LplyUL").html();
	
});
 */

</script>



<script id="template"  type="text/x-handlebars-template">
{{#each .}}

<div class="row thumb-pad" style="color: white;  border: 1px solid white; margin: 5px 0 5px 0; padding:3px;" >
				
		<div class="span4" style="margin-bottom: 5px; margin-top:5px;"> 
		<small class="label label-danger">{{comment_idx}}</small>
			{{userid}}  
		
		</div>
		<div class="span8" style="margin-bottom: 5px;"> 
		<small class="label label-danger" style="float:right;">{{post_date}}</small>
		</div>
		<div class="span8" style="margin-bottom: 2px;">
					<p>{{content}}</p>
					
			 <a href="#" class="btn btn-success tm_style_4" style="margin-bottom: 2px;">Read More</a>
		</div>
</div>


{{/each}}
</script>


<%@  include file="../include/footer.jsp" %>
