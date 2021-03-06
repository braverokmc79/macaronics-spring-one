<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
	<title>Macarnoics</title>
	<meta charset="utf-8">
	<link rel="icon" href="/resources/template/img/favicon.ico" type="image/x-icon">
	<link rel="shortcut icon" href="/resources/template/img/favicon.ico" type="image/x-icon" />
	<meta name="description" content="Your description">
	<meta name="keywords" content="Your keywords">
	<meta name="author" content="Your name">
	
	
	<!-- include libraries(jQuery, bootstrap) 충돌 섬머노트 -->
 <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
 <!-- <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>   -->
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>

<!--  스마트 에디터 -->
<script type="text/javascript" src="/resources/smarteditor2.0/js/service/HuskyEZCreator.js" charset="utf-8"></script>	
	
	
<link rel="stylesheet" href="/resources/template/css/bootstrap.css" type="text/css" media="screen"> 
	<link rel="stylesheet" href="/resources/template/css/responsive.css" type="text/css" media="screen">
	<link rel="stylesheet" href="/resources/template/css/style.css" type="text/css" media="screen">
	
	
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
	 <script type="text/javascript" src="/resources/template/js/jquery.js"></script>  
	<script type="text/javascript" src="/resources/template/js/superfish.js"></script>
    <script type="text/javascript" src="/resources/template/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="/resources/template/js/jquery.cookie.js"></script>
		
	<link rel="stylesheet" href="/resources/template/css/touchTouch.css" type="text/css" media="screen">
	<link rel="stylesheet" href="/resources/template/css/kwicks-slider.css" type="text/css" media="screen">

	<script type="text/javascript" src="/resources/template/js/jquery.flexslider-min.js"></script>
	<script type="text/javascript" src="/resources/template/js/jquery.kwicks-1.5.1.js"></script>
   
	<script type="text/javascript" src="/resources/template/js/touchTouch.jquery.js"></script>
	
	
	<script type="text/javascript">if($(window).width()>1024){document.write("<"+"script src='/resources/template/js/jquery.preloader.js'></"+"script>");}	</script>
	<script>		
		 jQuery(window).load(function() {	
		 $x = $(window).width();		
	if($x > 1024)
	{			
	jQuery("#content .row").preloader();    }			 
		
		 jQuery('.spinner').animate({'opacity':0},1000,'easeOutCubic',function (){jQuery(this).css('display','none')});	
  		  }); 
					
	</script>

	<!--[if lt IE 8]>
  		<div style='text-align:center'><a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/img/upgrade.jpg"border="0"alt=""/></a></div>  
 	<![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->
	<!--<![endif]-->
	<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link rel="stylesheet" href="/resources/template/css/docs.css" type="text/css" media="screen">
    <link rel="stylesheet" href="/resources/template/css/ie.css" type="text/css" media="screen">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400' rel='stylesheet' type='text/css'>
  <![endif]-->


	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<link rel="stylesheet" href="//cdn.jsdelivr.net/highlight.js/8.7/styles/monokai_sublime.min.css">
<script src="//cdn.jsdelivr.net/highlight.js/8.7/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>



	</head>




<%@ include file="../include/menu.jsp"  %>




<div class="bg-content">       
  <!--============================== content =================================-->      

<div class="container" style="">

<h3>이메일 쓰기</h3>
<p style="color:red;">${param.message }</p>

<form action="/mail/mail_sender.do" method="post" style="background-color: white;"  id="form1">
<table class="table" style="background-color: white;">

<tr>
 <th>발신자 이름</th>
 <td><input name="senderName" class="form-control"></td>
</tr>

<tr>
 <th>발신자 이메일</th>
 <td><input name="senderMail" class="form-control"></td>
</tr>

<tr>
 <th>수신자 이메일 주소</th>
 <td><input name="receiveMail"  class="form-control"></td>
</tr>


<tr>
 <th>제목</th>
 <td><input name="subject" class="form-control" ></td>
</tr>
 

<tr>
 <th>메시지</th>
 <td><textarea name="message"  class="form-control" id="message"></textarea>
 
 <script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "message",
    sSkinURI: "/resources/smarteditor2.0/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});



</script>
								
						
 
 </td>
</tr>




<tr>
<td colspan="2">
<input type="submit" value="메일 보내기"  class="btn btn-info"  id="mailSubBtn"/>
</td>
</tr>
</table>



</form>



</div>

</div>




<script>

$(document).ready(function(){
	
	
	$("#mailSubBtn").click(function(){
		
		
		 submitContents($("#form1"));
		
	
	});

	

	
	
	
});



// ‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["message"].exec("UPDATE_CONTENTS_FIELD", []);
 
    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("ir1").value를 이용해서 처리한다.
 
    try {
        elClickedObj.form.submit();
    } catch(e) {}

}

</script>



<%@  include file="../include/footer.jsp" %>