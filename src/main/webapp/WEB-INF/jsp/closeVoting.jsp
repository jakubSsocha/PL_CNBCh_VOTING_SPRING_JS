<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Zamykanie głosowanie</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/mainStyle.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
</header>
<div class="col-lg-3"></div>
<div class="center-container">
    <div class="col-lg-6">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/messageContainer.jsp"></jsp:include>
        <form:form method="post" modelAttribute="voting">
        <div class="mainClose_container">
            <p class="main_text">Głosowanie</p>
            <div>Nazwa: <span id="name_validator"></span><form:input path="name" class="form-control" id="name"
                                                                     readonly="true"/></div>
            <div>Opis: <span id="description_validator"></span><form:input path="description" class="form-control"
                                                                           id="description" readonly="true"/></div>
            <div>Treść głosowania: <form:textarea path="text" class="form-control"
                                                  id="text" rows="5" readonly="true"/></div>
            <div>Głosowanie tajne
                <form:checkbox path="secret" class="form-control" id="secret" disabled="true"/>
            </div>
            <form:hidden path="id" value="${voting.id}"/>
            <form:hidden path="closed" value="${voting.closed}"/>
            <form:hidden path="active" value="${voting.active}"/>
            <form:hidden path="secret" value="${voting.secret}"/>
        </div>
        <div class="mainClose_container">
            <p class="main_text">Użytkownicy</p>
            <div><form:checkboxes path="users" items="${allUsers}" itemLabel="Name"
                                  class="form-control" id="users" disabled="true"/></div>
            <br/>
        </div>
        <div class="mainClose_container">
            <div><input type="submit" value="Zamknij głosowanie" class="btn btn-warning" id="submitForm"></div>
            </form:form>
        </div>
        <br/>
    </div>
</div>
<div class="col-lg-3"></div>
</body>
</html>
