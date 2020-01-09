<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kategori Düzenle</title>
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
	
		<h3>Kategori Düzenle</h3>
	
		<form method="POST" action="${pageContext.request.contextPath}/editCategory">
			<input type="hidden" name="id" value="${categories.id}" />
			<table>
				<tr>
					<td>Kategori Numarası : </td>
					<td>${categories.id}</td>
				</tr>		
				<tr>
					<td>Platform Adı : </td>
					<td>${categories.platformName}</td>	
				</tr>
				<tr>
					<td>Kategori Adı : </td>
					<td><input type="text" name="categoryName"  /></td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" value="Kaydet" /> 
					<a href="${pageContext.request.contextPath}/listCategory">İptal Et</a></td>
				</tr>
			</table>
		</form>
	<%} %>
	
	<jsp:include page="_footer.jsp"></jsp:include>
				

</body>
</html>