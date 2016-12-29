<%@page import="com.example.wbe04.model.shop.dto.CartDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../include/header.jsp"  %>

<%@ include file="../include/menu.jsp"  %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample6_address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('sample6_address2').focus();
            }
        }).open();
    }
</script>



<div class="bg-content">       
  <!--============================== content =================================-->      

<div class="container" >




      <article class="container" >
        <div class="page-header">
          <h3>회원 정보 수정 <small>basic form</small></h3>
        </div>
         <div class="col-md-3" >
         </div>
        <div class="col-md-6 col-md-offset-3" style="background-color: white; margin: 10px; padding: 10px; padding-left: 300px;">
          <form role="form" method="post" action="/member/memebrUpate.do">
          	 <div class="form-group">
              <label for="username">이름</label>
              <input type="text" class="form-control" id="username"   name="username"  value="${loginUser.username }">
            </div>
          
          
            <div class="form-group">
              <label for="InputEmail">이메일 주소</label>
              <input type="email" class="form-control" id="email"  name="email"  value="${loginUser.email }">
            </div>
           
            <div class="form-group">
              <label for="InputPassword1">아이디</label>
              <input type="text"  style="background-color: white;" class="form-control" id="userid" readonly="readonly"  name="userid" value="${loginUser.userid }">
            </div>
           
            <div class="form-group">
              <label for="InputPassword1">전화번호</label>
              <input type="text" class="form-control" id="tel"   name="tel"   placeholder="전화번호" value="${loginUser.tel }">
            </div>
           
           
            <div class="form-group">
              <label for="InputPassword2">비밀번호</label>
              <input type="password" class="form-control" id="userpw" placeholder="비밀번호" name="userpw" >
            </div>
       
     
     <hr>
                <input type="text" id="sample6_postcode" placeholder="우편번호"  name="zipcode" value="${loginUser.zipcode }">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="btn btn-success"><br>
			 
			 <div class="form-group" >
			    <input type="text" id="sample6_address" 
			    placeholder="주소"  name="address1" size="30" value="${loginUser.address1 }">
			    </div>
			  <div class="form-group">  
		       <input type="text" id="sample6_address2" placeholder="상세주소" name="address2" value="${loginUser.address2 }">
           </div>
         <hr>  
     
         
            <div class="form-group text-center">
              <button type="submit" class="btn btn-info" id="infoSubmit">정보수정<i class="fa fa-check spaceLeft"></i></button>
              <button type="submit" class="btn btn-warning">취소<i class="fa fa-times spaceLeft"></i></button>
            </div>
          </form>
        </div>

      </article>







</div>

</div>


<script >

$(document).ready(function(){
	
	var message ="${message}";
	if(message.length >3){
		alert(message);
	}
	
	
	
    
   
    $("#infoSubmit").click(function(event){
    	
    	event.preventDefault();
    	
    	var pw=$("#userpw").val();
    	if(pw.trim().length < 3){
    		
    		 alert("비밀번호 입력은 필수 입니다.");	
    		 $("#userpw").focus();
    		 return;
    	}
    	
    	
    	var form1 =$("form[role='form']");
    	form1.submit();
    	
    });
    	
    
   
   
});

</script>

<%@  include file="../include/footer.jsp" %>




