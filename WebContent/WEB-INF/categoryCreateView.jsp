<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kategori Oluştur</title>
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
		<h3>Kategori Oluştur</h3>

	<form method="POST" action="${pageContext.request.contextPath}/createCategory">
		<table>
			<tr>
				<td>Platform Adı : </td>
				<td>
					<select name="platformName">
						<c:forEach items="${platforms}" var = "platform">
							<option value="${platform.platformName}">${platform.platformName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Kategori Adı : </td>
				<td><input type="text" name="categoryName" required="required" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Kaydet" /> 
				<a href="listCategory">İptal Et</a></td>
			</tr>
		</table>
	</form>
	<%} %>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>