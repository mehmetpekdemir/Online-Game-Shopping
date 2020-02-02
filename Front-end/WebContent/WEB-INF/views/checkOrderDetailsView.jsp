<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	
	<style>
		#totalPrice {
			margin-top:10px;
			padding: 5px;
		}
	
	</style>
	<title>Sipariş Detayları</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<form method="POST" action="${pageContext.request.contextPath}/paymentDetail">
	
	<h3 class="text-info">Kayıtlı Adresler</h3>
	
	<table class="table table-dark table-striped">
		<tr >
			<th>Adres Başlığı</th>
			<th>Adres Detayı</th>
			<th>Şehir</th>
			<th>İlçe</th>
			<th>Posta Kodu</th>
		</tr>
		<c:forEach items="${customer.addresses}" var="address" >
			<tr>
				<td>${address.addressTitle}</td>
				<td>${address.addressDetail}</td>
				<td>${address.city}</td>
				<td>${address.district}</td>
				<td>${address.postCode}</td>
				<td><input type="radio" name="addressId" value="${address.id}" id="checkAddress" required="required"></td>				
				<td></td>
			</tr>
		</c:forEach>
	</table>
			<h3>Ödeme Seçenekleri</h3>
			<select class = "custom-select text-dark bg-light" name="paymentOption" required="required">
				<option value="1">Kapıda Ödeme</option>
				<option value="2">Takas Bakiyesi İle Kapıda Ödeme</option>
				<option value="3">Kredi Kartı İle Ödeme</option>
			</select>
			<div id="totalPrice">
				<p class="font-weight-bold" >Toplam Ücret : ${totalOrderPrice} </p>
			</div>
			
		<input class="btn btn-success" type="submit" name="submit" value="Onayla">
		<a class="btn btn-danger" href="listProduct">İptal Et</a></td>
	</form>
	<jsp:include page="_footer.jsp"></jsp:include>
	
	  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>