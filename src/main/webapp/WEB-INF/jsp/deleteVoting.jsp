<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Usuń głosowanie</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/deleteVoting.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
</header>
<div class="col-lg-3"></div>
<div class="col-lg-6">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/messageContainer.jsp"></jsp:include>
    <form:form method="post" modelAttribute="voting">
    <div class="mainOptions_container">
        <p class="mainOptions_text">Głosowanie</p>
        <div>Nazwa: <span id="name_validator"></span><form:input path="name" class="form-control" id="name" readonly="true"/></div>
        <div>Opis: <span id="description_validator"></span><form:input path="description" class="form-control"
                                                                       id="description" readonly="true"/></div>
        <div>Treść głosowania: <form:textarea path="text" class="form-control"
                                              id="text" rows="5" readonly="true"/></div>
        <div style="text-align: center">
            <div style="display: inline-block">Głosowanie aktywne <form:checkbox path="active" class="form-control"
                                                                                 id="active" disabled="true"/></div>
            <div style="display: inline-block; margin-left: 80px">Głosowanie tajne
                        <form:checkbox path="secret" class="form-control" id="secret" disabled="true"/>
            </div>
        </div>
            <form:hidden path="id" value="${voting.id}"/>
            <form:hidden path="closed" value="${voting.closed}"/>
    </div>
    <div class="mainOptions_container">
        <p class="mainOptions_text">Użytkownicy</p>
        <div><form:checkboxes path="users" items="${allUsers}" itemLabel="Name"
                                                    class="form-control" id="users" disabled="true"/></div>
        <br/>
    </div>
    <div class="mainOptions_container">
            <div><input type="submit" value="Usuń głosowanie" class="btn btn-danger" id="submitForm"></div>
        </form:form>
    </div>
    <br/>
</div>
<div class="col-lg-3"></div>
</body>
</html>
