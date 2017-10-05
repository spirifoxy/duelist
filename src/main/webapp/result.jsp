<%@ page import="duelist.spirifoxy.com.github.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% boolean isWinner = (boolean) request.getAttribute("isWinner"); %>
    <title><%= isWinner ? "Победа!" : "Поражение" %></title>
    <script src="<c:url value="/js/script.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/result.css" />"/>
</head>
<body>
    <div class="container">

        <div class="result">
            <%= isWinner ? "Победа!" : "Поражение" %>
        </div>

        <div class="stats">
            <div class="stat">
                <div class="name">Рейтинг</div>
                <div class="value"><%= isWinner ? "+1" : "-1" %></div>
            </div>

            <div class="stat">
                <div class="name">Урон</div>
                <div class="value">+1</div>
            </div>

            <div class="stat">
                <div class="name">HP</div>
                <div class="value">+1</div>
            </div>
        </div>

        <%--<div class="buttons-wrapper">--%>
            <a href="<c:url value="/duels"/>" class="button duels">Дуэли</a>
            <a href="<c:url value="/"/>" class="button exit">Главная</a>
        <%--</div>--%>
    </div>
    <div class="footer">page: 100ms, db: 5req (20 ms)</div>
</body>
</html>
