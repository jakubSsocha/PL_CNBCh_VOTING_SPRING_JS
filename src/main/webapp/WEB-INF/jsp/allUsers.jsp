<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Zarządzanie użytkownikami</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/allUsers.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/header.jsp"></jsp:include>
</header>
<div class="col-lg-2"></div>

<div class="col-lg-8">
    <div style="text-align: center; margin-bottom: 15px">
        <div class="mainOptions_container">
            <p class="mainOptions_text">Zarządzanie użytkownikami</p>
        </div>
    </div>
    <div class="panel-group" id="accordion">
        <c:forEach items="${allUsers}" var="user" varStatus="theCount">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${user.id}" class="buttons"
                           data-id="${user.id}">
                            <b>${theCount.index+1}. ${user.name}</b>
                        </a>
                    </h4>
                </div>
                <div id="collapse${user.id}" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="additionalOptions_container">
                            <p class="additionalOptions_text">Opcje:</p>
                            <c:choose>
                                <c:when test="${user.active == true}">
                                    <div style="text-align: center">
                                        <button class="btn btn-danger"
                                                onclick="window.location.href='/user/changeStatus/${user.id}'">
                                            Dezaktywuj Użytkownika
                                        </button>
                                        <button class="btn btn-primary"
                                                onclick="window.location.href='/user/changeRole/${user.id}'">Zmień Rolę
                                        </button>
                                    </div>
                                </c:when>
                                <c:when test="${user.active == false}">
                                    <div style="text-align: center">
                                        <button class="btn btn-success"
                                                onclick="window.location.href='/voting/result/${user.id}'">Aktywuj
                                            Użytkownika
                                        </button>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="additionalOptions_container">
                            <div id="roles${user.id}" style="text-align: center"></div><br />
                        </div>
                        <div class="additionalOptions_container">
                            <p class="additionalOptions_text">Informacje:</p>
                            <ul>
                                <li><b>status: </b><span id="status${user.id}"></span></li>
                                <li><b>email: </b><span id="email${user.id}"></span></li>
                                <li><b>data rejestracji: </b><span id="createdDate${user.id}"></span></li>
                            </ul>
                        </div>
                        <div class="additionalOptions_container">
                            <p class="additionalOptions_text">Wyniki głosowań:</p>
                            <ul id="results${user.id}"></ul>
                        </div>
                        <div class="additionalOptions_container">
                            <p class="additionalOptions_text">Komentarze:</p>
                            <p>Funkcjonalność zostanie dodana w przyszłości</p>
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
<script src="${pageContext.request.contextPath}/resources/js/allUsers.js" type="application/javascript"></script>
