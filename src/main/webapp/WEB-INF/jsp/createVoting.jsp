<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Utwórz nowe głosowanie</title>
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
    <form:form method="post" modelAttribute="voting">
    <div class="mainOptions_container">
        <p class="mainOptions_text">Nowe głosowanie</p>
        <div>Nazwa: <span id="name_validator"></span><form:input path="name" class="form-control" id="name"/></div>
        <div>Opis: <span id="description_validator"></span><form:input path="description" class="form-control"
                                                                       id="description"/></div>
        <div>Treść głosowania: <form:textarea path="text" class="form-control"
                                              id="text" rows="5"/></div>
        <div style="text-align: center">
            <div style="display: inline-block">Głosowanie aktywne <form:checkbox path="active" class="form-control"
                                                                                 id="active"/></div>
            <div style="display: inline-block; margin-left: 80px">Głosowanie tajne
                <c:choose>
                    <c:when test="${voting.secret == true}">
                        <form:checkbox path="secret" class="form-control" id="secret" disabled="true"/>
                    </c:when>
                    <c:otherwise>
                        <form:checkbox path="secret" class="form-control" id="secret"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <c:if test="${not empty voting.id}">
            <form:hidden path="id" value="${voting.id}"/>
            <form:hidden path="createdDate" value="${voting.createdDate}"/>
            <form:hidden path="closed" value="${voting.closed}"/>
        </c:if>
    </div>
    <div class="mainOptions_container">
        <p class="mainOptions_text">Dodaj użytkowników</p>
        <div>Dostępni użytkownicy: <form:checkboxes path="users" items="${allUsers}" itemLabel="Name"
                                                class="form-control" id="users"/></div>
        <br/>
    </div>
    <div class="mainOptions_container">
        <c:if test="${empty voting.id}">
            <div><input type="submit" value="Utwórz głosowanie" class="btn btn-success" id="submitForm"></div>
        </c:if>
        <c:if test="${not empty voting.id}">
            <div><input type="submit" value="Zapisz zmiany" class="btn btn-success" id="submitForm"></div>
        </c:if>
        </form:form>
    </div>
    <br/>
</div>
<div class="col-lg-3"></div>
</body>
</html>
<script type="module" src="${pageContext.request.contextPath}/resources/js/createVoting.js"
        type="application/javascript"></script>
