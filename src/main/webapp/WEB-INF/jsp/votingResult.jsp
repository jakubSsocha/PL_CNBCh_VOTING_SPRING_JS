<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Głosuj:</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/votingResult.css" rel="stylesheet">
</head>
<body>
<div class="col-lg-2"></div>
<div class="col-lg-8" style="text-align: center">
    <p class="mainOptions_text">
        Wyniki głosowania: ${voting.name}
    </p>

    <div class="mainOptions_container">
        <p class="additionalOptions_text">Dotyczące:</p><br />
        <div style="text-align: justify">${voting.text}</div><br/>
    </div>

    <div class="additionalOptions_container">
        <p class="additionalOptions_text">Treść:</p>
        <div style="text-align: justify">${voting.description}</div>
    </div>

    <div class="additionalOptions_container">
        <p class="additionalOptions_text">Wyniki głosowania:</p>
        <div style="text-align: left">
            Uprawnionych do głosowania: ${fn:length(voting.results)}
            <c:forEach items="${voting.results}" var="result" varStatus="theCount">
                <p>${theCount.index + 1}. ${result.user.name} głos: ${result.vote}</p>
            </c:forEach>
        </div>
    </div>

    <div class="additionalOptions_container">
        <p class="additionalOptions_text">Głosowanie zakończono:
            ${voting.closedDate.format( DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"))}</p>
    </div>

</div>
<div class="col-lg-2"></div>
</body>
</html>
