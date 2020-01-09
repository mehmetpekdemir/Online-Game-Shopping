<%@page import="com.handstand.entity.Admin"%>
<%@page import="com.handstand.entity.Customer"%>
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
		<a href="${pageContext.request.contextPath}/listProduct">Ürün Listesi</a>|
		<%
			Admin loginedAdmin =(Admin) MyUtils.getLoginedAdmin(session);
			if (loginedAdmin == null) {
				Customer loginedCustomer =(Customer) MyUtils.getLoginedCustomer(session);
				if(loginedCustomer!=null){
		%>	
			<a href="${pageContext.request.contextPath}/orderInfo">Sepetim</a>|
			<a href="${pageContext.request.contextPath}/customerInfo">Profilim</a>|
			<a href="${pageContext.request.contextPath}/customerExit">Çıkış Yap</a>
			
				<% } else { %>
					<a href="${pageContext.request.contextPath}/userLogin">Giriş Yap</a>|
					<a href="${pageContext.request.contextPath}/customerRegister">Kayıt Ekranı</a>|
				<%} %>
		<% } else { %>		
			<a href="${pageContext.request.contextPath}/listCategory">Kategoriler </a> |
			<a href="${pageContext.request.contextPath}/listPlatform">Platformlar </a> |
			<a href="${pageContext.request.contextPath}/adminInfo">Admin Profili</a>|
			<a href="${pageContext.request.contextPath}/adminExit">Çıkış Yap</a>
		<%} %>

	</div>


</body>
</html>