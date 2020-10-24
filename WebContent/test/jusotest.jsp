<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
http://www.juso.go.kr/addrlink/addrLinkUrl.do?confmKey=devU01TX0FVVEgyMDIwMTAxNzE0MTEwNDExMDI5ODg=&returnUrl=http://localhost:8080/test/jusotest.jsp
 -->
<%
	String roadFullAddr = request.getParameter("roadFullAddr");
%>

<h1><%=roadFullAddr %></h1>
</body>
</html>