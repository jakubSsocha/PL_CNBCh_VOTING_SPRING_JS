<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Strona logowania</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/bootstrap/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
</head>
<body>
<header>
    <header>
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/headers/nooneLoginHeader.jsp"></jsp:include>
    </header>
</header>
<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
        <div class="mainOptions_container">
            <p class="mainOptions_text">Logowanie do konta</p>
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
        <div class="additionalOptions_container">
            <p class="additionalOptions_text">Nie masz konta?
                <a class="additionalOptions_link" role="button" href="/user/add">
                    Zarejestruj się
                </a>
            </p>
            <p class="additionalOptions_text">Nie pamiętasz hasła?
                <a class="additionalOptions_link" role="button" href="#">
                    Przypomnij hasło
                </a>
            </p>
        </div>
    </div>
    <div class="col-lg-3"></div>
</div>
</body>
</html>
<script type="module" src="${pageContext.request.contextPath}/resources/js/login.js" type="application/javascript"></script>
