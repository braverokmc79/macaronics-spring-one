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
 <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>  
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>


	
	
	
<link rel="stylesheet" href="/resources/template/css/bootstrap.css" type="text/css" media="screen"> 
	<link rel="stylesheet" href="/resources/template/css/responsive.css" type="text/css" media="screen">
	<link rel="stylesheet" href="/resources/template/css/style.css" type="text/css" media="screen">
	
	
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>
	 <!-- <script type="text/javascript" src="/resources/template/js/jquery.js"></script>  -->
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


<div class="container">




 <h3>방명록 등록</h3>

<div class="mini-layout">
            <div class="mini-layout-body">
       	
	
	<div class="row">
	

		
		<div class="span12" style="margin-bottom: 10px; text-align: ;" >
	     	

        			<form role="form1" method="post" >
					<div>
				     	<c:choose>
								<c:when test="${not empty loginUser }">
								
								<div class="form-group">
								<label for="exampleInputEmail1">이름</label> 
								<input type="text"	 name='name' class="form-control" value="${loginUser.username }" readonly="readonly"  style="color:white; background: black;">	
								</div>
								
								
							    <div class="form-group">
								<label for="exampleInputEmail1">이메일</label> <input type="email"
								name='email' class="form-control" value="${loginUser.email }" readonly="readonly"  style="color:white; background: black;">
								 </div>
								
								</c:when>
								<c:otherwise>
								
								<div class="form-group">
								<label for="exampleInputEmail1">이름</label> 
								<input type="text"	name='name' class="form-control" placeholder="이름" style="color:white; background: black;" >
								</div>
								
							    <div class="form-group">
								<label for="exampleInputEmail1">이메일</label> <input type="email"
								name='email' class="form-control" placeholder="email">
								 </div>
										
								</c:otherwise>
							</c:choose>
						

						
						<div class="form-group">
							<label for="exampleInputEmail1">패스워드</label> <input type="password"
								name='passwd' class="form-control" >
						</div>
						
						
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3" id="content"></textarea>
						


			<!-- include summernote-ko-KR -->
			<script src="/summernote/lang/summernote-ko-KR.js"></script>
								
						<script type="text/javascript">
						$(document).ready(function() {
							//아이디가 content 에 서머노트를 적용 한다.
							  $('#content').summernote({
								  height:600  ,
								  lang: 'ko-KR' // default: 'en-US'
							  });
						});
						
						</script>
						
						</div>
				
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-info">등록하기</button>
						<button type="reset" class="btn btn-danger">취소</button>
					</div>
				</form>	  
		  
		  </div>
   


 
</div> <!-- row -end -->


            </div>
  </div>
	      



</div>
</div>
	         
	         
<%@  include file="../include/footer.jsp" %>

