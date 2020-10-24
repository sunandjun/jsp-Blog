<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<%@page import="java.net.URLDecoder"%>

<%
	Cookie[] cookies = request.getCookies();
	String myCookie = "";
	for (int i = 0; i < cookies.length; i++) {
		String name = cookies[i].getName();
		if (name.equals("remember")) { // name 이 id 인 쿠키의 value값을 가져옴
			myCookie = URLDecoder.decode(cookies[i].getValue(), "utf-8");
		}
	}
%>

<br />
<br />
<div class="container">
	<form action="/user?cmd=loginProc" method="post">

		<div class="form-group">
			<label>유저 이름:</label> <input type="text" class="form-control" placeholder="Enter User Name" name="username" value="<%=myCookie%>" />
		</div>
		<div class="form-group">
			<label>패스워드:</label> <input type="password" class="form-control" placeholder="Enter password" name="password" />
		</div>

		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember"> Remember me
			</label>
		</div>

		<button type="submit" class="btn btn-primary">로그인</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>