<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/messageContainer.css" rel="stylesheet">
</head>
<body>
<div
        <c:choose>
            <c:when test="${message.type == 'error'}">
                class="alert"
            </c:when>
            <c:when test="${message.type == 'success'}">
                class="success"
            </c:when>
        </c:choose>
            id="messageHandler"/>
    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
    <c:choose>
        <c:when test="${message.type == 'error'}">
            <strong>Uwaga!</strong> ${message.text}
        </c:when>
        <c:when test="${message.type == 'success'}">
            <strong>Sukces!</strong> ${message.text}
        </c:when>
    </c:choose>
</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/resources/js/messageContainer.js"></script>
