<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<link rel="stylesheet" type="text/css" href="css/style.css">
	<title>Ürün Oluştur</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	

	<p class="text-danger">${errorMessage}</p>

	<%
	Admin loginedAdmin = MyUtils.getLoginedAdmin(session);
		if (loginedAdmin == null) {
	%>
	<h3>Erişim Hakkınız Yok</h3>

	<%
		} else {
	%>
	
	<h3 class="text-info">Ürün Oluştur</h3>
		
	<form method="POST" action="${pageContext.request.contextPath}/createProduct">
		<table class="table table-dark table-striped font-weight-bold">
			<tr>
				<td>Platform Adı :</td>
				<td><select class = "custom-select text-white bg-dark" name="platformName" id="sel1"
					onchange="giveSelection(this.value)">
						<c:forEach items="${platforms}" var="platform">
							<option value="${platform.platformName}">${platform.platformName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Kategori Adı :</td>
				<td><select class = "custom-select text-white bg-dark" name="categoryName" id="sel2">
						<c:forEach items="${categories}" var="category">
							<option data-option="${category.platformName}">${category.categoryName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Ürünün Barkod Kodu</td>
				<td><input class="form-control font-weight-bold" type="text" name="code" value="${products.code}"
					required="required" /></td>
			</tr>
			<tr>
				<td>Ürünün Adı</td>
				<td><input class="form-control font-weight-bold" type="text" name="productName"
					value="${products.productName}" required="required" /></td>
			</tr>
			<tr>
				<td>Fiyatı</td>
				<td><input class="form-control font-weight-bold" type="text" name="price" value="${products.price}"
					required="required" /></td>
			</tr>
			<tr>
				<td>Stok Durumu</td>
				<td><input class="form-control font-weight-bold" type="text" name="stockAmount"
					value="${products.stockAmount}" required="required" /></td>
			</tr>
			<tr>
				<td>Ürünün Resmi</td>
				<td><input class="form-control font-weight-bold" type="file" name="image" value="${products.image}"
					required="required" /></td>
			</tr>
			<tr>
				<td>Ürünün Açıklaması</td>
				<td><input class="form-control font-weight-bold" type="text" name="description"
					value="${products.description}" required="required" /></td>
			</tr>

			<tr>
				<td></td>
				<td colspan="2"><input class="btn btn-success" type="submit" value="Kaydet" /> 
				<a class="btn btn-danger" href="listProduct">İptal Et</a></td>
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
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>