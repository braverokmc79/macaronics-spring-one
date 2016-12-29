<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"  %>

<%@ include file="../include/menu.jsp"  %>



<div class="bg-content">       
  <!--============================== content =================================-->      
      <div id="content">


<c:choose>
	<c:when test="${ empty message }">
		<h3>문서가 잘 저장 되었습니다.</h3>
	</c:when>
	<c:otherwise>
		<h3>${message }</h3>
	</c:otherwise>
</c:choose>



	<div style="margin-bottom: 50px;">
	
	
	
	</div>



</div>
</div>
<%@  include file="../include/footer.jsp" %>
