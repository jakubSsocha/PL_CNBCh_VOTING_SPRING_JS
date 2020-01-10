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
    <link href="${pageContext.request.contextPath}/resources/css/allVotings.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/headers/nooneLoginHeader.jsp"></jsp:include>
</header>
<div class="col-lg-3"></div>
<div class="col-lg-6">
    <div style="text-align: center; margin-bottom: 15px">
        <div class="mainOptions_container">
            <p class="mainOptions_text">Zarządzanie głosowaniami</p>
        </div>
    </div>
    <div class="panel-group" id="accordion">
        <c:forEach items="${allVotings}" var="voting" varStatus="theCount">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse${voting.id}" class="buttons"
                        data-id="${voting.id}">
                            <b>${theCount.index+1}. </b> ${voting.name} : ${voting.text}
                        </a>
                    </h4>
                </div>
                <div id="collapse${voting.id}" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="mainOptions_container">
                            <p class="mainOptions_text">Informacje ogólne:</p>
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
                        <div class="mainOptions_container">
                            <p class="mainOptions_text">Treść głosowania:</p>
                            <span id="text${voting.id}"></span>
                        </div>
                        <div class="mainOptions_container">
                            <p class="mainOptions_text">Głosujący:</p>
                            <div id="users${voting.id}">
                            </div>
                        </div>
                        <div class="mainOptions_container">
                            <p class="mainOptions_text">Komentarze:</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div class="col-lg-3"></div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/allVoting.js"></script>
