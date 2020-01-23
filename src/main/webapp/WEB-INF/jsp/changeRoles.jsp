<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Zmiana Ról użytkownika</title>
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
<div class="col-sm-3"></div>
<div class="col-sm-6">
    <div class="center-container">
        <div class="main_container">
            <p class="main_text">Zmień role</p>
            <div>Ustaw role użytkownika ${RolesDTO.name}:</div><br />
            <form:form method="post" modelAttribute="RolesDTO">
                <form:checkboxes path="roles" items="${Roles}" itemLabel="description"
                                 class="form-control"/>
                <br /><br />
                <input type="submit" value="Ustaw role" class="btn btn-success" id="submitForm">
            </form:form>
        </div>
    </div>
</div>
</div>
<div class="col-sm-3"></div>
</div>
</div>
</body>
</html>
