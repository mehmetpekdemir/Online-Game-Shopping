<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ürün Listesi</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<p style="color: red;">${errorMessage}</p>
	
	<h3>Ürün Listesi</h3>
		<%
			Admin loginedAdmin = MyUtils.getLoginedAdmin(session);
			if (loginedAdmin == null) {
		%>
		<form method="GET" action="${pageContext.request.contextPath}/productSearchPriceMin">
			<input style="float: left" type="submit" name="min" value = "En Düşük Fiyat"/>		
		</form>
		<form method="GET" action="${pageContext.request.contextPath}/productSearchPriceMax">
			<input style="float: left" type="submit" name="max" value = "En yüksek Fiyat"/>				
		</form>
		<br>
		<br>
  			<table border="1">
			<tr>
				<th>Platform Adı</th>
				<th>Kategori Adı</th>
				<th>Ürünün Adı</th>
				<th>Fiyat</th>
				<th>Stok durumu</th>	
				<th>Resim</th>		
				<th>Ürünün Açıklaması</th>
			</tr>
			
			<c:forEach items="${categories.products}" var="product">
			<tr>
				<td>${product.platformName}</td>
			 	<td>${product.categoryName}</td>
				<td>${product.productName}</td>
				<td>${product.price} ₺</td>
				<td>		
					<c:choose>
	    					<c:when test="${product.stockAmount>0 && product.stockAmount<=10 }">			
								Son ${product.stockAmount} adet ürün kaldı. Acele et :)
	    					</c:when>
	   					 	<c:when test="${product.stockAmount>10 }">			
								Ürün stokta var
	   						</c:when>
	    					<c:otherwise>
	        					Ürün stokta yok
	 				    </c:otherwise>
					</c:choose>
				</td>
				<td><img src = "img/${product.image}" alt = "Resim" height="150px" width="300px"></td>
				<td>${product.description }</td>
				<c:if test="${product.stockAmount != 0}">
				<td><a href="insertOrderItem?code=${product.code}">Ürünü Sepete Ekle</a></td>
				</c:if>
			</tr>
			</c:forEach>	
					
			</table>
		<% } else { %>
		
		<form method="GET" action="${pageContext.request.contextPath}/productSearchPriceMin">
			<input style="float: left" type="submit" name="min" value = "En Düşük Fiyat"/>		
		</form>
		<form method="GET" action="${pageContext.request.contextPath}/productSearchPriceMax">
			<input style="float: left" type="submit" name="max" value = "En yüksek Fiyat"/>				
		</form>
		<br>
		<br>
			<table border="1">
			<tr>
				
				<th>Ürünün Barkod Kodu</th>
				<th>Platform Adı</th>
				<th>Kategori Adı</th>
				<th>Ürünün Adı</th>
				<th>Ürünün Fiyatı</th>
				<th>Stok durumu</th>
				<th>Ürünün Resmi</th>		
				<th>Ürünün Açıklaması</th>	
				<th>Ürünü Düzenle</th>
				<th>Ürünü Sil</th>					
			</tr>
			
			<c:forEach items="${categories.products}" var="product">
			<tr>
				<td>${product.code}</td>
				<td>${product.platformName}</td>
				<td>${product.categoryName}</td>
				<td>${product.productName}</td>
				<td>${product.price}</td>
				<td>${product.stockAmount }</td>	
				<td><img src = "img/${product.image}" alt = "Resim" height="150px" width="300px"></td>
				<td>${product.description }</td>
				<td><a href="editProduct?code=${product.code}">Ürünü Düzenle</a></td>
				<td><a href="deleteProduct?code=${product.code}">Ürünü Sil</a></td>
			</tr>
			</c:forEach>
			</table>		
			<a href="createProduct">Yeni Ürün Oluştur</a>
		<%} %>

	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>