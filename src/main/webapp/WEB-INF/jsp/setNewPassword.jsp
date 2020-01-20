<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Zmiana hasła do konta</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/setNewPassword.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
</header>
<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
        <div class="mainOptions_container">
            <p class="mainOptions_text">Zmiana hasła do konta</p>
            <form:form method="post" modelAttribute="passwordChanger">
                <div>Stare hasło: <span id="oldPassword_validator"></span>
                    <form:password path="oldPassword" class="form-control" id="oldPassword"/></div><br />
                <div>Nowe Hasło: <span id="newPassword_validator"></span>
                    <form:password path="newPassword" class="form-control" id="newPassword"/></div>
                <div>Powtórz nowe hasło: <span id="repeatedNewPassword_validator"></span>
                    <form:password path="repeatedNewPassword" class="form-control" id="repeatedNewPassword"/></div><br />
                <div>Ukryj hasła <input type="checkbox" value="ukryj hasła" checked id="hidePassword"></div><br />
                <div><input type="submit" value="Zmień hasło" class="btn btn-success" id="submitForm"></div>
            </form:form>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>
</body>
</html>
<script type="module" src="${pageContext.request.contextPath}/resources/js/setNewPassword.js" type="application/javascript"></script>
