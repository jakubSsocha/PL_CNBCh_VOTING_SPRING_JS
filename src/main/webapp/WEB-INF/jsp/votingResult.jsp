<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Głosuj:</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/mainStyle.css" rel="stylesheet">
</head>
<body>
<div class="col-lg-2"></div>
<div class="col-lg-8">
    <div class="center-container">
    <div class="main_container">
    <p class="main_text">
        Wyniki głosowania uchwały: ${voting.name}
    </p>
    </div>
    <div class="main_container">
        <c:choose>
            <c:when test="${votingResult.result == true}">
                <p class="main_text" style="color: forestgreen;"> Uchwała przyjęta </p>
            </c:when>
            <c:otherwise>
                <p class="main_text" style="color:red"> Uchwała odrzucona </p>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="main_container">
        <p class="additional_text">dotyczące:</p><br/>
        <div style="text-align: justify">${voting.description}</div>
        <br/>
    </div>

    <div class="main_container">
        <p class="additional_text">treść:</p>
        <div style="text-align: justify">${voting.text}</div>
    </div>

    <div class="main_container">
        <p class="additional_text">Wyniki głosowania:</p>
        <div style="text-align: left">
            <p>Uprawnionych do głosowania: ${fn:length(votingResult.users)}</p>
            <p>większość zwykła: ${votingResult.majority}</p>
            <p>Oddanych głosów: ${votingResult.votes}</p><br />
            <p>ZA: ${votingResult.yes}</p>
            <p>PRZECIW: ${votingResult.no}</p>
            <p>WSTRZYMUJĘ SIĘ: ${votingResult.abstain}</p>
        </div>
    </div>

    <div class="main_container">
        <p class="additional_text">wyniki szczegółowe:</p>
        <div style="text-align: left">
            <br/>
            <table style="width:100%" class="table table-bordered">
                <tr>
                    <th>Lp.</th>
                    <th>Użytkownik</th>
                    <th>Głos</th>
                    <th>Data oddania głosu</th>
                </tr>
                <c:forEach items="${votingResult.users}" var="result" varStatus="theCount">
                    <tr>
                        <td>${theCount.index+1}</td>
                        <td>${result.user.name}</td>
                        <c:choose>
                            <c:when test="${voting.secret eq true}">
                                <c:if test="${not empty result.vote}">
                                    <td>Głos oddany</td>
                                </c:if>
                                <c:if test="${empty result.vote}">
                                    <td></td>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <td>${result.vote}</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${result.userVotedDate.format( DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"))}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="main_container">
        <p class="additional_text">głosowanie zakończono:
            ${voting.closedDate.format( DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"))}</p>
    </div>

    <div class="main_container">
        <p class="additional_text">podpisy:</p><br /><br />
        <p>..........................</p>
        <p>Przewodniczący Rady Naukowej CNBCh UW</p>
    </div>
    </div>
</div>
<div class="col-lg-2"></div>
</body>
</html>
