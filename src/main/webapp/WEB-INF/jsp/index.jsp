<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Strona główna</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/bootstrap/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet">
</head>
<body>
<header>
<div class="container-fluid">
    <ul class="nav navbar-nav navbar-left">
        <li><a class="navbar-brand" href="http://www.cnbch.uw.edu.pl">CNBCh UW</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="/login" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in"></span> Zaloguj się</a></li>
    </ul>
</div>
</header>
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-center">
            <p>${text}</p>
            <img src="${pageContext.request.contextPath}/resources/img/cnbch.png"/>
        </div>
        <div class="col-sm-2 sidenav"></div>
    </div>
</div>
</body>
</html>
