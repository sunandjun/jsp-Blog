<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<br />
<br />
<div class="container">
	<form action="/post?cmd=saveProc" method="post">
	
		<input type="hidden" name="userId" value="${sessionScope.principal.id }" />
 
		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter Title" name="title"
			required="required"/>
		</div>
		<div class="form-group">
			<textarea id="summernote"  name="content"></textarea>
		</div>

		<button type="submit" class="btn btn-primary">글쓰기완료</button>
	</form>
</div>
<script>
  $('#summernote').summernote({
    tabsize: 2,
    height: 300
  });
</script>
<%@ include file="../layout/footer.jsp" %>