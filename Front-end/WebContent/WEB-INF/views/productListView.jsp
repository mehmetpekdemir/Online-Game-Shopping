<%@page import="com.handstand.util.MyUtils"%>
<%@page import="com.handstand.entity.Admin"%>
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
    <title>Ürün Listesi</title>
</head>

<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>

    <p class="text-danger">${errorMessage}</p>
    
	<h3 class="text-info">Ürün Listesi</h3>
	
    <div class="btn-group">
    	<form method="GET" action="${pageContext.request.contextPath}/productSearchPriceMin">
        	<input class="btn btn-danger" type="submit" name="min" value="En Düşük Fiyat" />
   		</form>
    	<form method="GET" action="${pageContext.request.contextPath}/productSearchPriceMax">
        	<input class="btn btn-info" type="submit" name="max" value="En yüksek Fiyat" />
    	</form>
    </div>
    
    <%
			Admin loginedAdmin = MyUtils.getLoginedAdmin(session);
			if (loginedAdmin == null) {
    %>
  
    <br>
    <table class="table table-dark table-striped">
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
                <td><img src="img/${product.image}" alt="Resim" height="150px" width="300px"></td>
                <td>${product.description }</td>
                <c:if test="${product.stockAmount != 0}">
                    <td><a class="badge badge-pill badge-success" href="insertOrderItem?code=${product.code}">Ürünü Sepete Ekle</a></td>
                </c:if>
            </tr>
        </c:forEach>

    </table>
    <% } else { %>
    <a class="badge badge-pill badge-success a-lg" href="createProduct">Yeni Ürün Oluştur</a>
    <br>
    <table class="table table-dark table-striped">
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
                <td>${product.price} ₺</td>
                <td>${product.stockAmount }</td>
                <td><img src="img/${product.image}" alt="Resim" height="150px" width="300px"></td>
                <td>${product.description }</td>
                <td><a class="badge badge-pill badge-success" href="editProduct?code=${product.code}">Ürünü Düzenle</a></td>
                <td><a class="badge badge-pill badge-danger" href="deleteProduct?code=${product.code}">Ürünü Sil</a></td>
            </tr>
        </c:forEach>
         
    </table>
   
    <%} %>

    <jsp:include page="_footer.jsp"></jsp:include>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>
