<%--
  Created by IntelliJ IDEA.
  User: Mevlin
  Date: 29/01/2023
  Time: 02:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
    <title>Planetarium der Deutschen Einheit</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user == null}">
        <p>
            <%@include file="login.jsp"%>
            <%@include file="register.jsp"%>
        </p>
    </c:when>
    <c:otherwise>
        <p>
            Logged in as ${sessionScope.userName}
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </p>
    </c:otherwise>
</c:choose>
<h1>Willkommen im Planetarium der Deutschen Einheit${sessionScope.user != null ? ', ' += sessionScope.userName : ''}!</h1>
<a href="default">Reload!</a>
<a href="default?user=Karl">Reload as Karl!</a>
<a href="${pageContext.request.contextPath}/browse">Browse available showings</a>
</body>
</html>
