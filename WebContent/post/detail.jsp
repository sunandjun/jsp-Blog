<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<c:if test="${sessionScope.principal.id == post.userId }">
		<br /> <br />
		<a href="/post?cmd=updateForm&id=${post.id }" 
			class="btn btn-warning">수정</a>
		<button onclick="postDelete(${post.id})" class="btn btn-danger">삭제</button>
	</c:if>
	<br /> <br />
	<h6 class="m-2">
		작성자 : <i> ${post.userId }</i> 조회수 : <i>${post.readCount}</i>
	</h6>
	<br />
	<h3 class="m-2">
		<b>${post.title }</b>
	</h3>
	<hr />
	<div class="form-group">
		<div class="m-2">${post.content }</div>
	</div>
	
	<hr />
</div>

<script type="text/javascript">
	
	function postDelete(id) {
		 
		fetch("http://localhost:8080/post?cmd=deleteProc&id="+id,{
				method:"post"
			}) //pending(램)
		.then(function(res){
			//다운된 된 후 실행 (파싱)
			//res => Promise 객체 (다운 받은 데이터)
			//res.text(); //문자열 파싱
			//res.json(); //json 파싱
			return res.text();
			})
		.then(function(res){
			//위에서 리턴한 res가 넘어온다.
			console.log(res);
			if(res == "ok"){
					alert("삭제성공");
					location.href="/";
				}else{
					alert("삭제실패");
					history.back();	
				}
			});//주소요청할때 사용  
		//fetch 는 비동기 함수이다 
		//fetch는 Promise 를 가지고 있다.
		
	}
</script>

<%@ include file="../layout/footer.jsp"%>