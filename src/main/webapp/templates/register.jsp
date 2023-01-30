<%--
  Created by IntelliJ IDEA.
  User: Mevlin
  Date: 30/01/2023
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
    Register
    <form name="registerForm>" method="post" action="${pageContext.request.contextPath}/register">
        <span class="labelledFormField">
            <label for="emailInput">Email:</label>
            <input type="text" id="emailInput" name="email">
        </span>
        <span class="labelledFormField">
            <label for="passwordInput">Password:</label>
            <input type="password" id="passwordInput" name="password">
        </span>
        <input type="submit" id="submitInput" value="Register">
    </form>
</div>
