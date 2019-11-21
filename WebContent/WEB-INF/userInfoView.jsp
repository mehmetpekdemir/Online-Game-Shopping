<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kullanıcı Detaylı Bilgi</title>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_menu.jsp"></jsp:include>

	<h3>Hoşgeldin : ${user.firstName}</h3>
	<h3>Kullanıcı Bilgilerini Düzenle</h3>

		<form method="POST" action="${pageContext.request.contextPath}/editUser">
			<input type="hidden" name="emailAddress" value="${user.emailAddress}" />
			<table>
				<tr>
					<td>Email : </td>
					<td>${user.emailAddress}</td>
				</tr>
				<tr>
					<td>Adı : </td>
					<td><input type="text" name="itemName" value="${user.firstName}" /></td>
				</tr>
				<tr>
					<td>Soyadı : </td>
					<td><input type="text" name="itemName" value="${user.lastName}" /></td>
				</tr>
				<tr>
					<td>Şifre : </td>
					<td><input type="text" name="price" value="${user.password}" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Kaydet" /> 
					<a href="${pageContext.request.contextPath}/home">İptal Et</a></td>
				</tr>
			</table>
		</form>

	<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>