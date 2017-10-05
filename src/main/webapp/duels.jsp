<%@ page import="duelist.spirifoxy.com.github.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Дуэли</title>
    <script src="<c:url value="/js/script.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/duels.css" />"/>
</head>
<body>
    <div class="container">
        <div class="rating">
            <div class="caption">Ваш рейтинг:</div>
            <div class="value"><%= ((User)session.getAttribute("user")).getRating() %></div>
        </div>
        <button class="start-duel" id="startDuel">Начать дуэль</button>
    </div>
</body>
</html>
