<%@ page import="duelist.spirifoxy.com.github.model.Room" %>
<%@ page import="duelist.spirifoxy.com.github.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Room room = (Room)session.getAttribute("room");
        User opponent = (User)session.getAttribute("opponent");
    %>
    <title>Подготовка к бою</title>

    <meta http-equiv="Refresh" content="<%= room.getTimeToStart() %>;url=<c:url value="/duel"/>">
    <script src="<c:url value="/js/script.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/preparing.css" />"/>
</head>
<body>

    <div class="container">
        <div class="enemy">
            <div class="caption">Ваш враг:</div>
            <div class="name"><%= opponent.getUsername() %></div>
        </div>
        <div class="timer">
            <div class="caption">Дуэль начнется через</div>
            <div class="value" id="timer"><%= room.getTimeToStart() %></div>
        </div>
    </div>
    <div class="footer">page: 100ms, db: 5req (20 ms)</div>

</body>
</html>
