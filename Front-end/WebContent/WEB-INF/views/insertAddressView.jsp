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
	<title>Yeni Adres Ekleme</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3 class="text-info" >Adres Kayıt</h3>

	<form method="POST" action="${pageContext.request.contextPath}/insertAddress">
		<table class="table table-dark table-striped font-weight-bold">
			<tr>
				<td>Adres Başlığı :</td>
				<td><input class="form-control font-weight-bold" type="text" name="addressTitle" value="${address.addressTitle}" required="required"></td>
			</tr>
			<tr>
				<td>Adres Detayları :</td>
				<td><input class="form-control font-weight-bold" type="text" name="addressDetail" value="${address.addressDetail}" required="required"></td>
			</tr>
			<tr>
				<td>Şehir :</td>
				<td><input class="form-control font-weight-bold" type="text" name="city" value="${address.city}" required="required"></td>
			</tr>
			<tr>
				<td>İlçe :</td>
				<td><input class="form-control font-weight-bold" type="text" name="district" value="${address.district}"  required="required"></td>
			</tr>
			<tr>
				<td>Posta Kodu :</td>
				<td><input class="form-control font-weight-bold" type="text" name="postCode" value="${address.postCode}" required="required"></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="btn btn-success" type="submit" name="submit" value="Kaydet">
				<a class="btn btn-danger" href="${pageContext.request.contextPath}/customerInfo">İptal Et</a></td>
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