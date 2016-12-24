<%@page import="com.example.wbe04.model.shop.dto.CartDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"  %>

<%@ include file="../include/menu.jsp"  %>


<%@ include file="../include/sessionCheck.jsp"  %>

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









	
	<td></td>

	<td>${row.hit}</td>

	<td style="text-align: center;">

	</td>
	
	





</tbody>


<tfoot>
<tr>
<td colspan="2" style="text-align: center;">
	<input type="submit" value="확인" class="btn btn-info"/>
</td>
</tr>

</tfoot>

</table>
</div>
		
</form>





</div>
</div>






<%@  include file="../include/footer.jsp" %>
