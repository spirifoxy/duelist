<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />"/>
</head>
<body>
    <div class="container">
        <a href="<c:url value="/duels"/>" class="button duels">Дуэли</a>
        <a href="<c:url value="/logout"/>" class="button exit">Выход</a>
    </div>
    <div class="footer">page: 100ms, db: 5req (20 ms)</div>
</body>
</html>
