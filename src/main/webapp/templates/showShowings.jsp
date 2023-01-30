<%--
  Created by IntelliJ IDEA.
  User: Mevlin
  Date: 30/01/2023
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
    <title>Available Showings</title>
</head>
<body>
<h3>Available Showings:</h3>
<table>
    <tr>
        <th>Title</th>
        <th>Start Date and Time</th>
        <th>Duration</th>
        <th>Hall Number</th>
    </tr>
    <c:forEach items="${requestScope.showings}" var="showing">
        <tr>
            <td>${showing.title()}</td>
            <td><fmt:formatDate
                    type="both"
                    dateStyle="long"
                    timeStyle="medium"
                    value="${showing.startDateTimeAsDate()}"
            /></td>
            <td><fmt:formatNumber type="number" minIntegerDigits="2" value="${(showing.duration() / 3600).intValue()}"
            />:<fmt:formatNumber type="number" minIntegerDigits="2" value="${((showing.duration() % 3600) / 60).intValue()}"/>h</td>
            <td>${showing.hallNumber()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
