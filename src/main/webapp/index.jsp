<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />"/>
</head>
<body>
<a href="<c:url value="/main"/>" class="button">Дуэли</a>
<a href="<c:url value="/logout"/>" class="button">Выход</a>
</body>
</html>
