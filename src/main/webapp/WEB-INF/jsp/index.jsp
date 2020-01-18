<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Strona główna</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet">
</head>
<body>
<header>
    <header>
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
    </header>
</header>
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-3 sidenav"></div>
        <div class="col-sm-6 text-center">
            <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/messageContainer.jsp"></jsp:include>
                <img src="${pageContext.request.contextPath}/resources/img/cnbch.png"/>
        </div>
        <div class="col-sm-3 sidenav"></div>
    </div>
</div>
</body>
</html>
