<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Głosuj:</title>
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
<div class="col-lg-6">
    <div class="center-container">
        <form:form method="post" modelAttribute="result">
        <div class="main_container">
            <p class="main_text">Głosowanie</p>
            <div>Nazwa: <span id="name_validator"></span><form:input path="voting.name" class="form-control" id="name"
                                                                     readonly="true"/></div>
            <div>Opis: <span id="description_validator"></span><form:input path="voting.description"
                                                                           class="form-control"
                                                                           id="description" readonly="true"/></div>
            <div>Treść głosowania: <form:textarea path="voting.text" class="form-control"
                                                  id="text" rows="8" readonly="true"/></div>
            <div>Głosowanie tajne
                <form:checkbox path="voting.secret" class="form-control" id="secret" disabled="true"/>
            </div>
        </div>
        <div class="main_container">
            <p class="main_text">Głosuj:</p>
            <form:radiobuttons items="${votingResults}" path="vote" class="form-control votingOptions"/>
        </div>
        <div class="main_container">
            <div><input type="submit" value="Oddaj głos" class="btn btn-success" id="button"></div>
            </form:form>
        </div>
        <br/>
    </div>
</div>
<div class="col-lg-3"></div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/vote.js" type="application/javascript"></script>
