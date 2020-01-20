<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Wyniki głosowń</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/allVotings.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
</header>
<div class="col-lg-3"></div>
<div class="col-lg-6" style="text-align: center">
    <div class="mainOptions_container">
        <p class="mainOptions_text">Wyniki głosowań:</p>
    </div>
    <c:forEach items="${votings}" var="voting" varStatus="theCount">
        <a href="/voting/result/${voting.id}" style="text-decoration: none">
            <div class="additionalOptions_container">
                <p class="additionalOptions_text">
                        ${voting.name} : ${voting.description}
                </p>
            </div>
        </a>
    </c:forEach>
</div>
<div class="col-lg-3"></div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/userVotings.js" type="module" type="application/javascript"></script>
