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
    <link href="${pageContext.request.contextPath}/resources/css/createVoting.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
</header>
<div class="col-lg-3"></div>
<div class="col-lg-6">
    <form:form method="post" modelAttribute="result">
    <div class="mainOptions_container">
        <p class="mainOptions_text">Głosowanie</p>
        <div>Nazwa: <span id="name_validator"></span><form:input path="voting.name" class="form-control" id="name" readonly="true"/></div>
        <div>Opis: <span id="description_validator"></span><form:input path="voting.description" class="form-control"
                                                                       id="description" readonly="true"/></div>
        <div>Treść głosowania: <form:textarea path="voting.text" class="form-control"
                                              id="text" rows="8" readonly="true"/></div>
        <div style="text-align: center">
            <div style="display: inline-block">Głosowanie aktywne <form:checkbox path="voting.active" class="form-control"
                                                                                 id="active" disabled="true"/></div>
            <div style="display: inline-block; margin-left: 80px">Głosowanie tajne
                        <form:checkbox path="voting.secret" class="form-control" id="secret" disabled="true"/>
            </div>
        </div>
    </div>
    <div class="mainOptions_container">
        <p class="mainOptions_text">Głosuj:</p>
        <div style="text-align: center">
            <form:radiobuttons items="${votingResults}" path="vote" class="form-control"/>
        </div>
    </div>
    <div class="mainOptions_container">
            <div><input type="submit" value="Oddaj głos" class="btn btn-success"></div>
            </form:form>
    </div>
    <br/>
</div>
<div class="col-lg-3"></div>
</body>
</html>
