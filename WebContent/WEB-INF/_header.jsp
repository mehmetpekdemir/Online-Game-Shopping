<%@page import="com.handstand.entity.Admin"%>
<%@page import="com.handstand.entity.Customer"%>
<%@page import="com.handstand.util.MyUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
		<div style="float: left">
			<h1>Anasayfa</h1>
		</div>
		
		<div style="float: right; padding: 10px; text-align: left;">
		
				<form method="GET" action="${pageContext.request.contextPath}/productSearch">
				Ürün ara : <input type="text" name="search" placeholder="İsme Göre Ürün Ara" required="required">
		</form>
		</div>
		<div style="float: right; padding: 10px; text-align: right;">

		<%
			Admin loginedAdmin =(Admin) MyUtils.getLoginedAdmin(session);
			if (loginedAdmin == null) {	
				Customer loginedCustomer =(Customer) MyUtils.getLoginedCustomer(session);
				if(loginedCustomer==null){
		%>		
				Hoşgeldin 
				<%}else{%>
				Hoşgeldin <b>${customer.firstName}</b>
		<% }} else { %>
			Hoşgeldin <b>${admin.firstName}</b>
		<%} %>
		</div>
 	
</body>
</html>