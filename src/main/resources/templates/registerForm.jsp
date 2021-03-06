<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <title>Spittr</title>
    <link rel="stylesheet" type="text/css"
          href=<c:url value="/resources/style.css" />
</head>
<body>
<h1>Register</h1>
<form method="POST" th:object="${spitter}">
    <div class="errors" th:if="${#fields.hasErrors('*')}">
        <ul>
            <li th:each="err : ${#fields.errors('*')}"
                th:text="${err}">Input is incorrect</li>
        </ul>
    First Name: <sf:input path="firstName" /><br/>
    Last Name: <sf:input path="lastName" /><br/>
    Email: <sf:input path="email" /><br/>
    Username: <sf:input path="username" /><br/>
    Password: <sf:password path="password" /><br/>
    <input type="submit" value="Register" />
</sf:form>
</body>
</html>