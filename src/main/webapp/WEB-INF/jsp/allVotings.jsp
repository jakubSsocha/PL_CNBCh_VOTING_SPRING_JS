<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Zarządzanie głosowaniami</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/mainStyle.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
</header>
<div class="col-lg-2"></div>
<div class="col-lg-8">
    <div class="center-container">
        <div class="main_container">
            <p class="main_text">Zarządzanie głosowaniami</p>
        </div>
        <a href="/voting/add" style="text-decoration: none; color: black">
        <div class="link_big" id="goToAddVotingForm">
            <p class="additional_text">Dodaj nowe głosowanie</p>
        </div>
        </a>
    </div>
    <div class="panel-group" id="accordion">
        <c:forEach items="${allVotings}" var="voting" varStatus="theCount">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${voting.id}" class="buttons"
                        data-id="${voting.id}">
                            <b>${theCount.index+1}. ${voting.name} : ${voting.text}</b>
                        </a>
                    </h4>
                </div>
                <div id="collapse${voting.id}" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="additional_container">
                            <p class="additional_text">Opcje:</p>
                            <c:choose>
                                <c:when test="${voting.closed == false}">
                                    <div class="center-container">
                                        <button class="btn btn-primary" onclick="window.location.href='/voting/edit/${voting.id}'">Edytuj</button>
                                        <button class="btn btn-warning" onclick="window.location.href='/voting/close/${voting.id}'">Zamknij</button>
                                        <button class="btn btn-danger" onclick="window.location.href='/voting/delete/${voting.id}'">Usuń</button>
                                    </div>
                                </c:when>
                                <c:when test="${voting.closed == true}">
                                    <div class="center-container">
                                        <button class="btn btn-success" onclick="window.location.href='/voting/result/${voting.id}'">Wyniki</button>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="additional_container">
                            <p class="additional_text">Informacje ogólne:</p>
                            <p>Czy głosowanie jest:</p>
                            <ul>
                                <li><b>zamknięte: </b><span id="closed${voting.id}"></span></li>
                                <li><b>aktywne: </b><span id="active${voting.id}"></span></li>
                                <li><b>tajne: </b><span id="secret${voting.id}"></span></li>
                            </ul>
                            <p>Dane podstawowe:</p>
                            <ul>
                                <li><b>data utworzenia: </b><span id="createdDate${voting.id}"></span></li>
                                <li><b>data ostatniej modyfikacji: </b><span id="modificationDate${voting.id}"></span></li>
                                <li><b>data zamknięcia: </b><span id="closedDate${voting.id}"></span></li>
                            </ul>
                        </div>
                        <div class="additional_container">
                            <p class="additional_text">Treść głosowania:</p>
                            <span id="text${voting.id}"></span>
                        </div>
                        <div class="additional_container">
                            <p class="additional_text">Głosujący:</p>
                            <div id="users${voting.id}">
                            </div>
                        </div>
                        <div class="additional_container">
                            <p class="additional_text">Komentarze:</p>
                            <div>Funkcjonalność zostanie dodana w przyszłości</div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div class="col-lg-2"></div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/allVotings.js" type="module" type="application/javascript"></script>
