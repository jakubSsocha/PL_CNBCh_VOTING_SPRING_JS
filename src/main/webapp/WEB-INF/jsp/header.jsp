<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<body>
<sec:authorize access="!isAuthenticated()">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/headers/headerBasic.jsp"></jsp:include>
</sec:authorize>
<sec:authorize access="hasRole('USER')&& !hasRole('ADMIN')">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/headers/headerUser.jsp"></jsp:include>
</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/headers/headerAdmin.jsp"></jsp:include>
</sec:authorize>
</body>
</html>
