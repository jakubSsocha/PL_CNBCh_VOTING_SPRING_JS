<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Strona logowania</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/mainStyle.css" rel="stylesheet">
</head>
<body>
<header>
    <header>
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
    </header>
</header>
<div class="col-lg-3"></div>
<div class="col-lg-6">
    <div class="center-container">
        <div class="main_container">
            <p class="main_text">Logowanie do konta</p>
            <form method="post">
                <div>Adres e-mail : <span id="loginInput_email_validator"></span>
                    <input type="text" name="username" class="form-control" id="loginInput_email"></div>
                <br/>
                <div>Hasło: <span id="loginInput_password_validator"></span>
                    <input type="password" name="password" class="form-control" id="loginInput_password"></div>
                <br/>
                <div><input type="submit" value="Zaloguj się" class="btn btn-success" id="loginInput_submit"/></div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <br/>
        </div>
        <div class="main_container">
            <p>Nie masz konta?
                <a class="main_link" role="button" href="/user/add">
                    Zarejestruj się
                </a>
            </p>
            <p>Nie pamiętasz hasła?
                <a class="main_link" role="button" href="#">
                    Przypomnij hasło
                </a>
            </p>
        </div>
    </div>
</div>
<div class="col-lg-3"></div>
</body>
</html>
<script type="module" src="${pageContext.request.contextPath}/resources/js/login.js"
        type="application/javascript"></script>
