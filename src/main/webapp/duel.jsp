<%@ page import="duelist.spirifoxy.com.github.model.Room" %>
<%@ page import="duelist.spirifoxy.com.github.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Room room = (Room)session.getAttribute("room");
        User currentUser = (User)session.getAttribute("user");
        User opponent = (User)session.getAttribute("opponent");
    %>

    <title>Дуэль с  <%= opponent.getUsername() %></title>
    <script src="<c:url value="/js/script.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/duel.css" />"/>
</head>
<body>

    <div class="container">
        <div>Комната № <%= room.getId() %></div>
        <div class="opponents">
            <div class="player">
                <div class="caption">Вы</div>
                <div class="name"><%= currentUser.getUsername() %></div>
                <div class="progress-bar">
                    <div class="progress" style="width: <%= currentUser.getHp() %>%"> </div>
                    <div id="userHp"> <%= currentUser.getHp() %></div> <%--TODO TEMP, remove in future--%>
                </div>
                <div class="damage" id="userDamage"> <%= currentUser.getDamage() %> ед.ур.</div>
            </div>

            <button class="attack-button" id="attack">ATK</button>

            <div class="player">
                <div class="caption">Враг</div>
                <div class="name"><%= opponent.getUsername() %></div>
                <div class="progress-bar">
                    <div class="progress" style="width: <%= opponent.getHp() %>%"> </div>
                    <div id="opponentHp"><%= opponent.getHp() %></div> <%--TODO TEMP, remove in future--%>
                </div>
                <div class="damage" id="opponentDamage"><%= opponent.getDamage() %> ед. ур.</div>

            </div>

        </div>

        <div class="log">
        </div>
    </div>
    <div class="footer">page: 100ms, db: 5req (20 ms)</div>
</body>
</html>
