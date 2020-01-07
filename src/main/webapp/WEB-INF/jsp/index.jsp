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
    <header>
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/headers/nooneLoginHeader.jsp"></jsp:include>
    </header>
</header>
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-3 sidenav"></div>
        <div class="col-sm-6 text-center">
            <div
                    <c:choose>
                        <c:when test="${message.type == 'error'}">
                            class="mainOptions_container_error"
                        </c:when>
                        <c:otherwise>
                            class="mainOptions_container_success"
                        </c:otherwise>
                    </c:choose>
            >
                <p class="mainOptions_text">${message.text}</p>
            </div>
                <img src="${pageContext.request.contextPath}/resources/img/cnbch.png"/>
        </div>
        <div class="col-sm-3 sidenav"></div>
    </div>
</div>
</body>
</html>
