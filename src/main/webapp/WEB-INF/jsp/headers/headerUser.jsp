<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<link href="${pageContext.request.contextPath}/resources/css/headerStyle.css" rel="stylesheet">
<sec:authorize access="hasRole('USER')">
<div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="http://www.cnbch.uw.edu.pl">CNBCh UW</a>
    </div>
    <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="/">Home</a></li>
        <li><a href="/password/setNew">Zmień hasło</a></li>
        <li><a href="/user/votings">Głosuj</a></li>
        <li><a href="/user/results">Wyniki Głosowań</a></li>
        <li><a href="#" onclick="document.getElementById('logout_form').submit(); return false;">
            <span class="glyphicon glyphicon-log-out"></span> Wyloguj się</a></li>
            <form action="<c:url value="/logout"/>" method="post" id="logout_form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
    </ul>
</div>
</sec:authorize>
</html>
