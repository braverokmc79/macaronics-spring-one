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
	
</style>


<div class="bg-content">       
  <!--============================== content =================================-->      

<div class="container" style="">

<h3>게시판 </h3>
<div style="float: right; margin-bottom: 10px;">
	<a class="btn btn-info"   href="/board/write.do" >글쓰기</a>
</div>

<div class="table-responsive">
<table class="table table-bordered table-striped" style="background-color: white; margin: 10px 0 10px 0;">
<colgroup>

<col class="col-lg-1">
<col class="col-lg-7"></colgroup>



<thead>
<tr class="alt">
<th><code>번호</code> </th>
<th><code>글쓴이</code> </th>
<th><code>제목</code> </th>
<th><code>등록 날짜</code> </th>
<th><code>조회수</code> </th>
<th><code>첨부파일</code> </th>
<th><code>다운로드</code> </th>
<th><code>게시물 그룹 (ref)</code> </th>
<th><code>답변 단계(depth)</code> </th>
<th><code>그룹 내에서의 순서 (reorder)</code> </th>
</tr>
</thead>

<tbody id="divList" style="margin-bottom: 30px;">

<c:forEach items="${list }" var="row">
	<tr>
	
	<td>${row.idx}</td>
	
	<td>${row.username}</td>
	
	<td>${row.subject}</td>
	
	<td>${row.post_date}</td>

	<td>${row.hit}</td>

	<td style="text-align: center;">
	<c:if test="${ not empty row.fileName }">
	<a href="/board/down.do?idx=${row.idx }&fileName=${row.fileName }"> <img src="/resources/template/img/file.png"/></a>
	</c:if>
	
	</td>
	<td>${row.hit}</td>


	<td>${row.down}</td>
	<td>${row.depth}</td>
	<td>${row.reorder}</td>
	
	
	</tr>

</c:forEach>

</tbody>


<tfoot>
<tr>

<td colspan="10" style="text-align: center;">
   <div class="pagination">
	  <ul>
	  <c:if test="${ page.curPage > 1}">
	  	  	<li><a href="/board/board_list.do?curPage=1">[시작]</a></li>
	  </c:if>
	  
	   <c:if test="${ page.curBlock > 1 }">
	   	<li><a href="/board/board_list.do?curPage=${page.prevPage }">&laquo;</a></li>
	   </c:if>
	       
	  <c:forEach  begin="${ page.blockStart }" end="${ page.blockEnd }" step="1"  var="pageNum">	 
		<c:choose>
		  <c:when test="${ param.curPage == pageNum }">
		       <li class="active">
	  	 	<a href="/board/board_list.do?curPage=${pageNum}" style="background:#E85356; color:white; ">${pageNum }</a>
	  	 		</li>
		  </c:when>
		  <c:otherwise>
		       <li>
	  	 	<a href="/board/board_list.do?curPage=${pageNum}">${pageNum }</a>
	  	 		</li>
		  </c:otherwise>
		</c:choose>
		
	  </c:forEach>
	 	 
	 	<c:if test="${page.curBlock <= page.totBlock }" >
	 		<li><a href="/board/board_list.do?curPage=${page.nextPage}">&raquo;</a></li>
	 	</c:if>   
	  
	  <!-- 마지막 페이지 -->
	  <c:if test="${page.curPage < page.totPage}">
	    <li><a href="/board/board_list.do?curPage=${page.totPage }">[끝]</a></li>
	  </c:if>
	    
	    
	  </ul>
  </div>
 </td>
</tr>
</tfoot>

</table>
</div>
		



</div>
</div>
<%@  include file="../include/footer.jsp" %>
