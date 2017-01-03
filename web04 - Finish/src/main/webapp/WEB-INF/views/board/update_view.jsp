<%@page import="com.example.wbe04.model.shop.dto.CartDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"  %>

<%@ include file="../include/menu.jsp"  %>

<!--   ckeditor 연결  -->
<script type="text/javascript" src="/ckeditor/ckeditor.js"></script>


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

<h3>게시판 수정하기 </h3>



<form name="form1" role="form1" method="post" enctype="multipart/form-data" >
<div class="table-responsive">



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

<input type="text" value="${view.subject }" name="subject">
</td>
</tr>



<tr>
<td>내용</td>
<td class="span12">
<textarea rows="5" class="form-control" name="content">${view.content }</textarea>
<!-- textarea 를 ckeditor 로 변경 시킴 -->
<script>
CKEDITOR.replace("content", {
	
	filebrowserUploadUrl :"/imageUpload.do"
   // filebrowserImageUploadUrl: 'MacaronicsServlet?command=ckeditor_upload'	
});
</script>



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
	
	<input type="hidden" name="oldFileName" value="${view.fileName }" >
	<input type="hidden" name="oldFileSize" value="${view.filesize }" >
	</c:if>
	&nbsp;&nbsp;&nbsp; 파일삭제 선택  : <input type="checkbox" name="fileCheck" > &nbsp;&nbsp;&nbsp;
	<input type="file" name="file1"  class="form-control">
 	
</td>
</tr>

</tbody>


<tfoot>
<tr>
<td colspan="2" style="text-align: center;">

<a href="/board/listPage" class="btn btn-info">목록 보기</a>

<input type="hidden" value="${view.idx}" name="idx" />
<input type="hidden" value="${view.ref}" name="ref" />
<input type="hidden" value="${view.reorder}" name="reorder" />



<button class="btn btn-warning" type="button"  id="btnUpdate">수정 하기</button>



</td>
</tr>

</tfoot>

</table>

</div>
		
</form>






<hr>


</div>
</div>




<script type="text/javascript">

$(document).ready(function(){

	
	$("#btnUpdate").click(function(){
		
		document.form1.action="/board/board_update.do";
		document.form1.submit();
		
	});
	
});	
</script>













<%@  include file="../include/footer.jsp" %>
