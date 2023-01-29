<%--
  Created by IntelliJ IDEA.
  User: Mevlin
  Date: 29/01/2023
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/styles.css">
    <title>Login</title>
</head>
<body>
<div class="form">
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
</div>
</body>
</html>
