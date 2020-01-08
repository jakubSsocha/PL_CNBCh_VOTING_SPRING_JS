<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Utwórz nowe głosowanie</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/bootstrap/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/createVoting.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/headers/nooneLoginHeader.jsp"></jsp:include>
</header>
<div class="col-lg-3"></div>
<div class="col-lg-6">
    <form:form method="post" modelAttribute="voting">
    <div class="mainOptions_container">
        <p class="mainOptions_text">Nowe głosowanie</p>
        <div>Nazwa: <span id="name_validator"></span><form:input path="name" class="form-control" id="name"/></div>
        <div>Opis: <span id="description_validator"></span><form:input path="description" class="form-control"
                                                                       id="description"/></div>
        <div>Treść głosowania: <form:textarea path="text" class="form-control"
                                                                                id="text" rows="5"/></div>
        <div style="text-align: center">
        <div style="display: inline-block">Głosowanie aktywne <form:checkbox path="active" class="form-control" id="active"/></div>
        <div style="display: inline-block; margin-left: 80px">Głosowanie tajne <form:checkbox path="secret" class="form-control" id="secret"/></div>
        </div>
    </div>
    <div class="mainOptions_container">
        <p class="mainOptions_text">Dodaj użytkowników</p>
        <div>Dostępni użytkownicy: <form:select path="users" items="${allUsers}" itemLabel="Name" multiple="true"
                                               class="form-control" id="users"/></div>
        <br/>
    </div>
    <div class="mainOptions_container">
        <div><input type="submit" value="Utwórz głosowanie" class="btn btn-success" id="submitForm"></div>
        </form:form>
    </div>
    <br/>
</div>
<div class="col-lg-3"></div>
</body>
</html>
<script type="module" src="${pageContext.request.contextPath}/resources/js/createVoting.js"
        type="application/javascript"></script>
