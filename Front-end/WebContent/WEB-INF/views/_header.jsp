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
	<title>Header</title>
</head>
<body>
		<div style="float: right; padding: 3px; text-align: left;">
			<form method="GET" action="${pageContext.request.contextPath}/productSearch">
				<span class ="text-dark">Ürün ara</span> : <input class = "text-dark" type="text" name="search" required="required">
			</form>
		</div>
		<div style="float: right; padding: 10px; text-align: right;">

		<%
			Admin loginedAdmin =(Admin) MyUtils.getLoginedAdmin(session);
			if (loginedAdmin == null) {	
				Customer loginedCustomer =(Customer) MyUtils.getLoginedCustomer(session);
				if(loginedCustomer==null){
		%>			
				<%}else{%>
				<p class="text-dark">Hoşgeldin ${customer.firstName}</p>
		<% }} else { %>
				<p class="text-dark">Hoşgeldin ${admin.firstName}</p>
		<%} %>
		</div>
		
		
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
 	
</body>
</html>