<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kategori Listesi</title>
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
		<h3>Kategori Listesi</h3>
			
			<table border="1">
				<tr>
					<th>Ürün Numarası</th>
					<th>Platform Adı</th>
					<th>Kategori Adı</th>
				</tr>
				<c:forEach items="${platform.categories}" var="category">
					<tr>
					<td>${category.id}</td>
					<td>${category.platformName}</td>
					<td>${category.categoryName}</td>
					<td><a href="editCategory?id=${category.id}&platformName=${category.platformName}">Kategoriyi Düzenle</a></td>
				</tr>
				</c:forEach>
			</table>
				<a href="createCategory">Yeni Kategori Oluştur</a>
		<%} %>
		
	
	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>