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
    <title>Üye Girişi</title>
</head>
<body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    
    <div>
        <form  method="POST" action="${pageContext.request.contextPath}/userLogin">
            <h3 class="text-info" >Üye Girişi</h3><br>
       
			<table class="table table-dark table-striped font-weight-bold">
			
			<tr>	
				<td>E-Mail : </td>
				<td><input class="form-control font-weight-bold" type="text" name="emailAddress" placeholder="E-Mail" required="required"></td>
			</tr>
			
			<tr>	
				<td>Şifre : </td>
				<td><input class="form-control font-weight-bold" type="password" name="password" placeholder="Şifre" required="required"></td>
			</tr>
			<tr>
				<td></td>
				<td>Şifremi Hatırla <input type="checkbox" name="rememberMe" value="Yes" id="remember"></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="btn btn-success" type="submit" name="submit" value="Giriş Yap" ></td>
			</tr>
			</table>              
        </form>
    </div>


    <jsp:include page="_footer.jsp"></jsp:include>
    
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>