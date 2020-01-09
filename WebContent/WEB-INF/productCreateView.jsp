<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ürün Oluştur</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Ürün Oluştur</h3>

	<p style="color: red;">${errorMessage}</p>

	<%
	Admin loginedAdmin = MyUtils.getLoginedAdmin(session);
		if (loginedAdmin == null) {
	%>
	<h3>Erişim Hakkınız Yok</h3>

	<%
		} else {
	%>

	<form method="POST"
		action="${pageContext.request.contextPath}/createProduct">
		<table>
			<tr>
				<td>Platform Adı :</td>
				<td><select name="platformName" id="sel1"
					onchange="giveSelection(this.value)">
						<c:forEach items="${platforms}" var="platform">
							<option value="${platform.platformName}">${platform.platformName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Kategori Adı :</td>
				<td><select name="categoryName" id="sel2">
						<c:forEach items="${categories}" var="category">
							<option data-option="${category.platformName}">${category.categoryName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Ürünün Barkod Kodu</td>
				<td><input type="text" name="code" value="${products.code}"
					required="required" /></td>
			</tr>
			<tr>
				<td>Ürünün Adı</td>
				<td><input type="text" name="productName"
					value="${products.productName}" required="required" /></td>
			</tr>
			<tr>
				<td>Fiyatı</td>
				<td><input type="text" name="price" value="${products.price}"
					required="required" /></td>
			</tr>
			<tr>
				<td>Stok Durumu</td>
				<td><input type="text" name="stockAmount"
					value="${products.stockAmount}" required="required" /></td>
			</tr>
			<tr>
				<td>Ürünün Resmi</td>
				<td><input type="file" name="image" value="${products.image}"
					required="required" /></td>
			</tr>
			<tr>
				<td>Ürünün Açıklaması</td>
				<td><input type="text" name="description"
					value="${products.description}" required="required" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Kaydet" /> 
				<a href="listProduct">İptal Et</a></td>
			</tr>
		</table>
	</form>
	<%
		}
	%>

	<script>
		var sel1 = document.querySelector('#sel1');
		var sel2 = document.querySelector('#sel2');
		var options2 = sel2.querySelectorAll('option');

		function giveSelection(selValue) {
			sel2.innerHTML = '';
			for (var i = 0; i < options2.length; i++) {
				if (options2[i].dataset.option === selValue) {
					sel2.appendChild(options2[i]);
				}
			}
		}

		giveSelection(sel1.value);
	</script>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>