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
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="http://www.cnbch.uw.edu.pl">CNBCh UW</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="/">Home</a></li>
        <li><a href="/user/login" style="margin-right: 10px"><span class="glyphicon glyphicon-log-in"></span> Zaloguj się</a></li>
    </ul>
</div>
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-center">
            <p>${text}</p>
            <img src="../images/cnbch.png"/>
        </div>
        <div class="col-sm-2 sidenav"></div>
    </div>
</div>

</body>
</html>
