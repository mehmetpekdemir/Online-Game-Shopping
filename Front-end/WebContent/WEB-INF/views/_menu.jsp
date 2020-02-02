<%@page import="com.handstand.entity.Admin"%>
<%@page import="com.handstand.entity.Customer"%>
<%@page import="com.handstand.util.MyUtils"%>
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
<title>Menu</title>
</head>
<body>

	<div id="menu">
		<a class="badge badge-pill badge-success" href="${pageContext.request.contextPath}/">Anasayfa</a> 
		<a class="badge badge-pill badge-info" href="${pageContext.request.contextPath}/listProduct">Ürün Listesi</a>
		<%
			Admin loginedAdmin =(Admin) MyUtils.getLoginedAdmin(session);
			if (loginedAdmin == null) {
				Customer loginedCustomer =(Customer) MyUtils.getLoginedCustomer(session);
				if(loginedCustomer!=null){
		%>	
			<a class="badge badge-pill badge-warning" href="${pageContext.request.contextPath}/orderInfo">Sepetim</a>
			<a class="badge badge-pill badge-primary" href="${pageContext.request.contextPath}/customerInfo">Profilim</a>
			<a class="badge badge-pill badge-secondary" href="${pageContext.request.contextPath}/customerExit">Çıkış Yap</a>
			
				<% } else { %>
					<a  class="badge badge-pill badge-warning" href="${pageContext.request.contextPath}/userLogin">Giriş Yap</a>
					<a  class="badge badge-pill badge-secondary" href="${pageContext.request.contextPath}/customerRegister">Kayıt Ol</a>
				<%} %>
		<% } else { %>		
			<a class="badge badge-pill badge-warning" href="${pageContext.request.contextPath}/listCategory">Kategoriler </a> 
			<a class="badge badge-pill badge-secondary" href="${pageContext.request.contextPath}/listPlatform">Platformlar </a> 
			<a class="badge badge-pill badge-dark" href="${pageContext.request.contextPath}/adminInfo">Admin Profili</a>
			<a class="badge badge-pill badge-danger" href="${pageContext.request.contextPath}/adminExit">Çıkış Yap</a>
		<%} %>

	</div>

	
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>