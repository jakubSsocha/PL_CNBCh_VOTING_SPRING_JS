<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Tworzenie nowego użytkownika</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/createUser.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/headers/nooneLoginHeader.jsp"></jsp:include>
</header>
<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
        <div class="mainOptions_container">
            <p class="mainOptions_text">Tworzenie nowego użytkownika</p>
            <form:form method="post" modelAttribute="user">
                <div>Imię: <span id="firstName_validator"></span><form:input path="firstName" class="form-control" id="firstName"/></div>
                <div>Nazwisko: <span id="lastName_validator"></span><form:input path="lastName" class="form-control" id="lastName"/></div>
                <div>e-mail: <span id="email_validator"></span><form:input path="email" class="form-control" id="email"/></div>
                <div>Hasło: <span id="password1_validator"></span><form:password path="password" class="form-control" id="password"/></div>
                <div>Powtórz hasło: <span id="password2_validator"></span><input type="password" class="form-control" id="password2"/></div><br />
                <div>Ukryj hasła <input type="checkbox" value="ukryj hasła" checked id="hidePassword"></div><br />
                <div><input type="submit" value="Zarejestruj" class="btn btn-success" id="submitForm"></div>
            </form:form>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>
</body>
</html>
<script type="module" src="${pageContext.request.contextPath}/resources/js/createUser.js" type="application/javascript"></script>
