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

<h3>답변 글쓰기 </h3>



<form name="from1" role="form1" method="post" enctype="multipart/form-data" action="/board/reply_insert.do">

<input type="hidden" name="idx" value="${dto.idx }">


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
<td>제목</td>
<td class="span12">
<input type="text" class="form-control" name="subject" value="Re : ${dto.subject }" />
</td>
</tr>

<tr>
<td>내용</td>

<td class="span12">
${dto.content }
<input type="hidden" name="content1" value="${dto.content }">
<textarea rows="5" class="form-control" name="content2"></textarea>
<!-- textarea 를 ckeditor 로 변경 시킴 -->
<script>
CKEDITOR.replace("content2", {
	
	filebrowserUploadUrl :"/imageUpload.do"
   // filebrowserImageUploadUrl: 'MacaronicsServlet?command=ckeditor_upload'	
});
</script>



</td>
</tr>


<tr>
<td>첨부파일</td>
<td>
<input type="file" name="file1"  class="form-control">
</td>
</tr>


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
