<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <p class="additionalOptions_text" style="font-size: 130%">
        Wyniki głosowania uchwały: ${voting.name}
    </p>
    <p class="additionalOptions_text">wynik głosowania:
        <c:choose>
            <c:when test="${votingResult.result == true}">
                <p style="color:forestgreen; font-weight: bold; font-size: 130%"> Uchwała przyjęta </p>
            </c:when>
            <c:otherwise>
                <p style="color:darkred; font-weight: bold; font-size: 130%"> Uchwała odrzucona </p>
            </c:otherwise>
        </c:choose>
    </p><br />
    <div class="mainOptions_container">
        <p class="additionalOptions_text">dotyczące:</p><br/>
        <div style="text-align: justify">${voting.description}</div>
        <br/>
    </div>

    <div class="additionalOptions_container">
        <p class="additionalOptions_text">treść:</p>
        <div style="text-align: justify">${voting.text}</div>
    </div>

    <div class="additionalOptions_container">
        <p class="additionalOptions_text">Wyniki głosowania:</p>
        <div style="text-align: left">
            <p>Uprawnionych do głosowania: ${votingResult.users}</p>
            <p>Oddanych głosów: ${votingResult.votes}</p>
            <p>TAK: ${votingResult.yes}</p>
            <p>NIE: ${votingResult.no}</p>
            <p>WSTRZYMUJĘ SIĘ: ${votingResult.abstain}</p>
            <p>większość zwykła: ${votingResult.majority}</p>
        </div>
    </div>

    <div class="additionalOptions_container">
        <p class="additionalOptions_text">wyniki szczegółowe:</p>
        <div style="text-align: left">
            <br/>
            <table style="width:100%" class="table table-bordered">
                <tr>
                    <th>Lp.</th>
                    <th>Użytkownik</th>
                    <th>Głos</th>
                    <th>Data oddania głosu</th>
                </tr>
                <c:forEach items="${voting.results}" var="result" varStatus="theCount">
                    <tr>
                        <td>${theCount.index+1}</td>
                        <td>${result.user.name}</td>
                        <td>${result.vote}</td>
                        <td>${result.userVotedDate.format( DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"))}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="additionalOptions_container">
        <p class="additionalOptions_text">głosowanie zakończono:
            ${voting.closedDate.format( DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"))}</p>
    </div><br /><br />

    <div class="additionalOptions_container">
        <p class="additionalOptions_text">podpisy:</p><br /><br />
        <p>..........................</p>
        <p>Przewodniczący Rady Naukowej CNBCh UW</p>
    </div>

</div>
<div class="col-lg-2"></div>
</body>
</html>
