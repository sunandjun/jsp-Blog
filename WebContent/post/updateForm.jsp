<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<br />
<br />
<div class="container">
	<input type="hidden" name="userId" value="${sessionScope.principal.id }" />

	<div class="form-group">
		<input type="text" class="form-control" placeholder="Enter Title" name="title"
		required="required" 
		value="${post.title }" />
	</div>
	<div class="form-group">
		<textarea id="summernote"  name="content" >${post.content }</textarea>
	</div>
	<button type="submit" onclick="postUpdate(${post.id})" class="btn btn-primary">수정완료</button>
	
</div>
<script>
  $('#summernote').summernote({
    tabsize: 2,
    height: 300
  });



  function postUpdate(id){

	  console.log(id);
	  
	  console.log(${content});
	  console.log(${post.userId});
	  var title = document.getElementsByName("title");
	  var content = document.getElementsByName("content");
	  
	  fetch("http://localhost:8080/post?cmd=updateProc",{
			method:"post",
			headers: {
			    "Content-Type": "application/x-www-form-urlencoded"
			  },
			  body: "id="+id+"&"+
				  "title="+title[0].value+"&" +
				  "content="+content[0].value+"&"+
				  "userId=${post.userId}"
		}) //pending(램)
	.then(function(res){

		return res.text();
		})
	.then(function(res){
		//위에서 리턴한 res가 넘어온다.
		
		if(res == "ok"){
				alert("수정성공");
				location.href="/";
			}else{
				alert("수정실패");
				//history.back();	
			}
		});
	}
</script>

<%@ include file="../layout/footer.jsp" %>