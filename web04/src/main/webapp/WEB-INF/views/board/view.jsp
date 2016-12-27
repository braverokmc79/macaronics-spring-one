<%@page import="com.example.wbe04.model.shop.dto.CartDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"  %>

<%@ include file="../include/menu.jsp"  %>

<style type="text/css">
	.pagination > ul >li a:hover{
	   background-color: #E85356;
	}
	.pagination > ul > .active{
		color:blue;
		text-decoration: none;
		
	}	
</style>


<%-- 
<%@ include file="../include/sessionCheck.jsp"  %> 
--%>

<!--   ckeditor 연결  -->
<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>



<div class="bg-content">       
  <!--============================== content =================================-->      

<div class="container" >

<h3>게시판 글쓰기 </h3>



<form name="form1" role="form1" method="post" enctype="multipart/form-data" >
<div class="table-responsive">

현재 로그인 유저 아이디 : <input type="text" name="userid" value="${loginUser.userid }" />
현재 로그인 한 유저 이름 :<input type="text" name="username" value="${loginUser.username }" />

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

     ${ view.fileName.substring(view.fileName.lastIndexOf("-")+1) } (  ${view.filesize } bytes ) &nbsp;&nbsp;&nbsp;
	<a href="/board/down.do?idx=${view.idx }&fileName=${view.fileName }"> <img src="/resources/template/img/file.png"/></a>
	</c:if>
	
 
</td>
</tr>

</tbody>


<tfoot>
<tr>
<td colspan="2" style="text-align: center;">

<a href="/board/listPage" class="btn btn-inf">목록 보기</a>
<c:if test="${ loginUser.userid != null}">

<button type="button" id="btnReply" class="btn btn-info">답변 달기</button>
</c:if>

<input type="hidden" value="${view.idx}" name="idx" />
<input type="text" value="${view.ref}" name="ref" />
<input type="text" value="${view.reorder}" name="reorder" />

<c:if test="${loginUser.userid ==view.userid }">

<button class="btn btn-danger" type="button"  id="btnDelete">삭제</button>
<button class="btn" type="button"  id="btnUpdate">수정</button>
</c:if>


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


   <div class="pagination">

  </div>
</div>
</div>






<script type="text/javascript">

$(document).ready(function(){
	var idx ="${param.idx}";
	var curpage=1;
	comment_list(idx, curpage)
	
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
					
					comment_list(board_idx, curpage);
					
					
					
				}else{
					alert("등록에 실패 하였습니다.");
				}
			}
			
		});	
		
	});
	
	$(".pagination").on("click", "ul > li a", function(event){
			event.preventDefault();
			
			page =$(this).attr("href");
			comment_list(idx, page.trim());
			//alert("클릭");
	});
	
	
	
});




function comment_list(board_idx, curpage){
	
 	$.getJSON("/board/comment_list.do?board_idx="+board_idx + "&curpage="+curpage, function(data){
		
		printData(data.commentList, $("#LplyUL"), $("#template"));
		pagination(data.pageMaker);
		
	});	
	
}



var printData =function (replyArr, targetDiv, handleBarTemplateName){
	
	var template =Handlebars.compile(handleBarTemplateName.html());
	
	var html =template(replyArr);
	targetDiv.html(html);
}



 
 
var pagination =function(pageMaker){

	 var idx ="${param.idx}";
	 
	 var str3 = "<ul>";
	 
	 if(pageMaker.cri.page >1){
		str3 += '<li><a href="1">[시작]</a></li>';
	 }
	 
	 if(pageMaker.prev){
		 str3 +="<li><a href='"+( pageMaker.startPage-1) +"' >&laquo;</a></li>";
	 }
	 
	 for(var i=pageMaker.startPage, len=pageMaker.endPage ; i <=len ; i++){
		 var strClass=pageMaker.cri.page ==i ? 'class="active"  style="background-color: #E85356;"' : '';
		 //str3 +="<li " +strClass + " > <a href='javascript:" +comment_list(idx, i)+";' >"+i+"</a></li>";
	 	str3 +="<li "+strClass+"><a href='"+i+"' >" +i +" </a></li>"
	 }
	 
	 
	 if(pageMaker.next){
		 str3 +="<li ><a href='"+( pageMaker.endPage +1)+ "' > &raquo;</a></li>";
	 }
	 
	 
	 if(pageMaker.cri.page < pageMaker.tempEndPage){
		 str3 +="<li><a href='"+pageMaker.tempEndPage+"'>[끝]</a></li>";
	 }
		 
	 
	str3 += "</ul>";
	 $(".pagination").html(str3);
	 

}
 
 
</script>



<!-- 답변  -->

<script type="text/javascript">

$(document).ready(function(){
	
	
	$("#btnReply").click(function(){
		document.form1.action="/board/board_reply.do";
		
		document.form1.submit();
	});
	
	
	
	/* 삭제 */
	$("#btnDelete").click(function(){
		
		//댓글이 있는지 확인
		var  liplySize=$("#LplyUL").find(".thumb-pad").size();
		
		if(liplySize > 0){
			alert("댓글이 달린 게시물은 삭제 할 수 없습니다.");	
			return ;
		}
		
		if(confirm("정말 삭제 하시겠습니까?")){
			
			document.form1.action="/board/board_delete.do";
			document.form1.submit();
		}
	});
	
	
   var replyMsg ="${replyMsg}";
   if(replyMsg.length>3){
	   alert(replyMsg);
   }
	
	
	/* 수정 */
	$("#btnUpdate").click(function(){
		
		document.form1.action="/board/board_update_view.do";
		document.form1.submit();
	});
	
	
	
});



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
