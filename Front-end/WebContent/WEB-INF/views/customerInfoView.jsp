<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<link rel="stylesheet" type="text/css" href="css/style.css">  
	<title>Kullanıcı Detaylı Bilgi</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>


	<h3 class="text-info" >Kullanıcı Bilgilerini Düzenle</h3>
	<div class="btn-group" style="margin:5px;">
		<a class="btn btn-success" href="insertAddress?emailAddress=${customer.emailAddress}">Yeni Adres Oluştur</a> 
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/listAddress">Kayıtlı Adreslerimi Görüntüle</a> 
	</div>

	
	<form method="POST" action="${pageContext.request.contextPath}/editCustomer">
		<input type="hidden" name="emailAddress" value="${customer.emailAddress}" />
		<table  class="font-weight-bold">
			<tr>
				<td>Email Adresi:</td>
				<td>${customer.emailAddress}</td>
			</tr>
			<tr>
				<td>Adı :</td>
				<td><input class="font-weight-bold" type="text" name="firstName"
					value="${customer.firstName}" /></td>
			</tr>
			<tr>
				<td>Soyadı :</td>
				<td><input class="font-weight-bold" type="text" name="lastName"
					value="${customer.lastName}" /></td>
			</tr>
			<tr>
				<td>Şifre :</td>
				<td><input class="font-weight-bold" type="password" name="password"
					value="${customer.password}" /></td>
			</tr>
			<tr>
				<td>Telefon Numarası :</td>
				<td><input class="font-weight-bold" type="tel" name="phoneNumber"
					value="${customer.phoneNumber}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn btn-success" type="submit" value="Kaydet" /> 
				<a class="btn btn-danger" href="${pageContext.request.contextPath}/home">İptal Et</a></td>
			</tr>
		</table>
	</form>
	

	<jsp:include page="_footer.jsp"></jsp:include>
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>