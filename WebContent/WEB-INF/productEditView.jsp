<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ürünleri Düzenle</title>
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
	
	<h3>Ürünleri Düzenle</h3>

		<form method="POST" action="${pageContext.request.contextPath}/editProduct">
			<input type="hidden" name="code" value="${products.code}" />
			<table>
				<tr>
					<td>Ürün Barkod Kodu : </td>
					<td>${products.code}</td>
				</tr>
				
				<tr>
					<td>Ürünün Platform Adı : </td>
					<td>${products.platformName}</td>
				</tr>
				
				<tr>
					<td>Ürünün Kategori Adı : </td>
					<td>${products.categoryName}</td>
				</tr>
					
				<tr>
					<td>Ürünün Adı : </td>
					<td><input type="text" name="productName" value="${products.productName}" /></td>
				</tr>
				<tr>
					<td>Ürünün Fiyatı : </td>
					<td><input type="text" name="price" value="${products.price}"/></td>
				</tr>
				<tr>
					<td>Stok Durumu</td>
					<td><input type="text" name="stockAmount" value="${products.stockAmount}" required="required"/></td>
				</tr>
				<tr>
					<td>Ürünün Resmi</td>
					<td><input type="file" name="image" value="${products.image}" required="required"/></td>
				</tr>
				<tr>
					<td>Ürünün Açıklaması</td>
					<td><input type="text" name="description" value="${products.description}" required="required"/></td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" value="Kaydet" /> 
					<a href="${pageContext.request.contextPath}/listProduct">İptal Et</a></td>
				</tr>
			</table>
		</form>
	<%} %>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>