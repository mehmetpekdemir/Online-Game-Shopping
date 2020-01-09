<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Platform Oluştur</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<p style="color: red;">${errorMessage}</p>
	
	<%
	Admin loginedAdmin = MyUtils.getLoginedAdmin(session);
		if (loginedAdmin == null) {
	%>
		<h3>Erişim Hakkınız Yok</h3>
		
	<% } else { %>

		<h3>Platform Oluştur</h3>
		
	<form method="POST" action="${pageContext.request.contextPath}/createPlatform">
		<table>
			<tr>
				<td>Platform Adı</td>
				<td><input type="text" name="platformName" required="required" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Kaydet" /> 
				<a href="categoryList">İptal Et</a></td>
			</tr>
		</table>
	</form>
	<%} %>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>