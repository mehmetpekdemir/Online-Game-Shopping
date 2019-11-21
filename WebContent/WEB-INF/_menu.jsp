<%@page import="com.handstand.entity.Admin"%>
<%@page import="com.handstand.util.MyUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>
</head>
<body>

	<div style="padding: 5px;">
		<a href="${pageContext.request.contextPath}/">Anasayfa</a> |
		<a href="${pageContext.request.contextPath}/productList">Ürün Listesi</a>|
		<%
			Admin loginedAdmin =(Admin) MyUtils.getLoginedUser(session);
			if (loginedAdmin == null) {
		%>
  			<a href="${pageContext.request.contextPath}/userLogin">Giriş Yap</a>
		<% } else { %>
			<a href="${pageContext.request.contextPath}/adminInfo">Kullanıcı bilgileri</a> 
			<a href="${pageContext.request.contextPath}/exit">Çıkış Yap</a>
		<%} %>

	</div>


</body>
</html>