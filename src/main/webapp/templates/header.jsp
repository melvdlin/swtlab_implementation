<%--
  Created by IntelliJ IDEA.
  User: Mevlin
  Date: 29/01/2023
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<div class="footer">
    <c:choose>
        <c:when test="${sessionScope.user == null}">
            <p>
                <a href="login">Login</a>
                <a href="default">Register</a>
            </p>
        </c:when>
    </c:choose>
</div>