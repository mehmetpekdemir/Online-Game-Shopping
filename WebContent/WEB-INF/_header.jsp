<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
		<div style="float: left">
			<h1>Anasayfa</h1>
		</div>
		<div style="float: right; padding: 10px; text-align: right;">
			Hoşgeldin <b>${user.firstName}</b> <br /> Arama <input name="search"> 
			<%
				//Buraya şart gelecek admin için farklı gösterim müşteri farklı gösterim yapılacak.
			%>
		</div>
</body>
</html>