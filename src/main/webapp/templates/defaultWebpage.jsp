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
    <title>Planetarium der Deutschen Einheit</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>Willkommen im Planetarium der Deutschen Einheit${param.user != null ? ', ' += param.user : ''}!</h1>
<a href="default">Reload!</a>
<a href="default?user=Karl">Reload as Karl!</a>
</body>
</html>
