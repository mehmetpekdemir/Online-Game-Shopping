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
	<title>Sepetim</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>
	
	<h3 class="text-info">Sepetim</h3>
	<table class="table table-dark table-striped">
		<tr>
			<th>Platform İsmi</th>
			<th>Kategori İsmi</th>
			<th>Ürün İsmi</th>
			<th>Ürün Resmi</th>
			<th>Ürün Adeti</th>
			<th>Ürün Toplam Fiyatı</th>
		</tr>
		<c:forEach items="${order.orderItems}" var="orderItem">
			<tr>
				<td>${orderItem.product.platformName}</td>
				<td>${orderItem.product.categoryName}</td>
				<td>${orderItem.product.productName}</td>
				<td><img src = "img/${orderItem.product.image}" alt = "Resim" height="150px" width="300px"></td>
				<td>${orderItem.quantity}</td>
				<td>${orderItem.totalPrice}</td>
				<td><a class="badge badge-pill badge-danger" href="deleteOrderItem?productId=${orderItem.product.id}">Ürünü Sepetimden Sil</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<a class="btn btn-danger" href="${pageContext.request.contextPath}/deleteOrder">Sepeti Boşalt</a>
	<a class="btn btn-success" href="${pageContext.request.contextPath}/payment">Sepeti Onayla (${totalOrderPrice})</a>
	<br><br>
	<jsp:include page="_footer.jsp"></jsp:include>
	
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>