<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Üye Girişi</title>
</head>
<body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    
    <div>
        <form  method="POST" action="${pageContext.request.contextPath}/userLogin">
            <h1>Üye Girişi</h1>
           
                <div>
                    <input type="text" name="emailAddress" value="${user.emailAddress}" placeholder="E-Mail" required="required">
                </div>

                <div>
                    <input type="password" name="password" value="${user.password}" placeholder="Şifre" required="required">
                </div>
                <div>
                    <div>
                        <input type="checkbox" name="rememberMe" value="Yes" id="remember">
                        <label for="remember">Şifremi Hatırla</label>
                    </div>
                </div>
                <input type="submit" name="submit" value="Giriş Yap" >        
        </form>
    </div>


    <jsp:include page="_footer.jsp"></jsp:include>
</body>

</html>