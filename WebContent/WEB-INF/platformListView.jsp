<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Platform Listesi</title>
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
	
		<h3>Platform Listesi</h3>
		
			<table border="1">
				<tr>
					<th>Platform Numarası</th>
					<th>Platform Adı</th>
				</tr>
				<c:forEach items="${platforms}" var="platform">
					<tr>
					<td>${platform.id}</td>
					<td>${platform.platformName}</td>
					<td><a href="editPlatform?id=${platform.id}">Platformu Düzenle</a></td>
				</tr>
				</c:forEach>
			</table>
				<a href="createPlatform">Yeni Platform Oluştur</a>
		<%} %>
		
	
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>