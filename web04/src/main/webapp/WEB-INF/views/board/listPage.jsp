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
	.control2 a:hover{
		color:blue;
		text-decoration: none;
	}	
</style>


<div class="bg-content">       
  <!--============================== content =================================-->      

<div class="container" style="">

<h3>게시판 </h3>
<div style="float: right; margin-bottom: 10px;">
	<a class="btn btn-info"   href="/board/write.do" >글쓰기</a>
</div>



<!-- 검색폼 -->
<form method="get" action="/board/listPage" >
<select name="searchType">
	 <option value="" <c:out value="${ param.searchType eq null ? 'selected' : ''  }" />  >
	 선택 </option>
  	<option value="userid" <c:out value="${ param.searchType eq 'userid' ? 'selected' : ''  }" />  >아이디</option>
	<option value="username" <c:out value="${ param.searchType eq 'username' ? 'selected' : ''  }" />  >글쓴이</option>
	<option value="subject" <c:out value="${ param.searchType eq 'subject' ? 'selected' : ''  }" />  >제목</option>
	<option value="content" <c:out value="${ param.searchType eq 'content' ? 'selected' : ''  }" /> >내용</option>
	<option value="all" <c:out value="${ param.searchType eq 'all' ? 'selected' : ''  }" />  >전체</option>
</select>
<input type="text"  name="keyword" value="${param.keyword }" ><button type="submit"  class="btn btn-info">검색</button>
</form>


<hr>


<div class="table-responsive">
<table class="table table-bordered table-striped" style="background-color: white; margin: 10px 0 10px 0;">
<colgroup>

<col class="col-lg-1">
<col class="col-lg-7"></colgroup>



<thead>
<tr class="alt">
<th><code>번호</code> </th>
<th><code>글쓴이</code> </th>
<th><code>제목 </code> </th>
<th><code>등록 날짜</code> </th>
<th><code>조회수</code> </th>
<th><code>첨부파일</code> </th>
<th><code>다운로드</code> </th>

<th><code>답변 단계(depth)</code> </th>
<th><code>그룹 내에서의 순서 (reorder)</code> </th>
</tr>
</thead>

<tbody id="divList" style="margin-bottom: 30px;">

<c:forEach items="${list }" var="row">
	<tr>
	
	<td>${row.idx}</td>
	
	<td>${row.username}</td>
	
	<td class="control2">
	<c:forEach begin="1" end="${row.depth }">
	 &nbsp;&nbsp;
	</c:forEach>
	
	<a href="/board/view.do?idx=${row.idx }">${row.subject}
	 <c:if test="${ row.comment_count > 0}" ><small style="color:blue;">[ ${row.comment_count } ]</small></c:if>
	</a></td>
	
	<td>${row.post_date}</td>

	<td>${row.hit}</td>

	<td style="text-align: center;">
	<c:if test="${ not empty row.fileName }">
	<a href="/board/down.do?idx=${row.idx }&fileName=${row.fileName }"> <img src="/resources/template/img/file.png"/></a>
	</c:if>
	
	</td>



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
	  <c:if test="${ param.page > 1}">
	  	  	<li><a href="/board/listPage${ pageMaker.makeQuery(1) }&searchType=${param.searchType }&keyword=${param.keyword }">[시작]</a></li>
	  </c:if>
	  
	   <c:if test="${ pageMaker.prev }">
	   	<li><a href="/board/listPage${ pageMaker.makeQuery(pageMaker.startPage - 1) }&searchType=${param.searchType }&keyword=${param.keyword }">&laquo;</a></li>
	   </c:if>
	       
	  <c:forEach  begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" step="1"  var="pageNum">	 
		<c:choose>
		  <c:when test="${ pageMaker.cri.page == pageNum }">
		       <li class="active">
	  	 	<a href="/board/listPage${pageMaker.makeQuery(pageNum) }&searchType=${param.searchType }&keyword=${param.keyword }" style="background:#E85356; color:white; ">${pageNum }</a>
	  	 		</li>
		  </c:when>
		  <c:otherwise>
		       <li>
	  	 	<a href="/board/listPage${pageMaker.makeQuery(pageNum) }&searchType=${param.searchType }&keyword=${param.keyword }">${pageNum }</a>
	  	 		</li>
		  </c:otherwise>
		</c:choose>
		
	  </c:forEach>
	 	 
	 	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }" >
	 		<li><a href="/board/listPage${pageMaker.makeQuery(pageMaker.endPage + 1) }&searchType=${param.searchType }&keyword=${param.keyword }">&raquo;</a></li>
	 	</c:if>   
	  
	  <!-- 마지막 페이지 -->
	  <c:if test="${ param.page < pageMaker.tempEndPage}">
	    <li><a href="/board/listPage${ pageMaker.makeQuery(pageMaker.tempEndPage) }&searchType=${param.searchType }&keyword=${param.keyword }">[끝]</a></li>
	  </c:if>
	    
	    
	  </ul>
  </div>
 </td>
</tr>
</tfoot>

</table>
</div>
		

<%--   <div class="pagination">
	  <ul>
	  <c:if test="${ param.page > 1}">
	  	  	<li><a href="/board/listPage${ pageMaker.makeSearch(1) }">[시작]</a></li>
	  </c:if>
	  
	   <c:if test="${ pageMaker.prev }">
	   	<li><a href="/board/listPage${ pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
	   </c:if>
	       
	  <c:forEach  begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" step="1"  var="pageNum">	 
		<c:choose>
		  <c:when test="${ pageMaker.cri.page == pageNum }">
		       <li class="active">
	  	 	<a href="/board/listPage${pageMaker.makeSearch(pageNum) }" style="background:#E85356; color:white; ">${pageNum }</a>
	  	 		</li>
		  </c:when>
		  <c:otherwise>
		       <li>
	  	 	<a href="/board/listPage${pageMaker.makeSearch(pageNum) }">${pageNum }</a>
	  	 		</li>
		  </c:otherwise>
		</c:choose>
		
	  </c:forEach>
	 	 
	 	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }" >
	 		<li><a href="/board/listPage${pageMaker.makeSearch(pageMaker.endPage + 1) }">&raquo;</a></li>
	 	</c:if>   
	  
	  <!-- 마지막 페이지 -->
	  <c:if test="${ param.page < pageMaker.tempEndPage}">
	    <li><a href="/board/listPage${ pageMaker.makeSearch(pageMaker.tempEndPage) }">[끝]</a></li>
	  </c:if>
	    
	    
	  </ul>
  </div> --%>





</div>
</div>
<%@  include file="../include/footer.jsp" %>
