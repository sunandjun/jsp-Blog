<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<script type="text/javascript">
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}
	function jusoCallBack(roadFullAddr){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		//document.form.roadFullAddr.value = roadFullAddr;
		document.querySelector("#address").value = roadFullAddr;
	}
</script>
<br />
<br />
<div class="container">
	<form action="/user?cmd=joinProc" method="post">
 
		<div class="form-group">
			<label >유저 이름:</label> 
			<input type="text" class="form-control" placeholder="Enter User Name" name="username"/>
		</div>
		<div class="form-group">
			<label >패스워드:</label> 
			<input type="password" class="form-control" placeholder="Enter password" name="password"/>
		</div>

		<div class="form-group">
			<label >이메일:</label> 
			<input type="email" class="form-control" placeholder="Enter email" name="email"/>
		</div>

		<div class="form-group">
			<label >주소:</label> 
			<button type="button" class="btn btn-warning float-right"
				onClick="goPopup();">주소검색</button>
			<input type="text" class="form-control" placeholder="Enter 주소" name="address" 
				id="address"
				readonly="readonly"/>
		</div>

		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>


<%@ include file="../layout/footer.jsp"%>