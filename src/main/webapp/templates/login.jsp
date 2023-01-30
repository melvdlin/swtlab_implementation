<%--
  Created by IntelliJ IDEA.
  User: Mevlin
  Date: 29/01/2023
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
    Login
    <form name="loginForm>" method="post" action="${pageContext.request.contextPath}/login">
        <span class="labelledFormField">
            <label for="emailInput">Email:</label>
            <input type="text" id="emailInput" name="email">
        </span>
        <span class="labelledFormField">
            <label for="passwordInput">Password:</label>
            <input type="password" id="passwordInput" name="password">
        </span>
        <input type="submit" id="submitInput" value="Login">
    </form>
    <c:if test="${requestScope.loginFailed}"><div class="loginFailedMessage">Login failed!</div></c:if>
</div>
