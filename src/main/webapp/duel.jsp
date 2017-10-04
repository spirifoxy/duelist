<%@ page import="duelist.spirifoxy.com.github.model.Room" %>
<%@ page import="duelist.spirifoxy.com.github.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Room room = (Room)session.getAttribute("room");
        User currentUser = room.getCurrentUser((User)session.getAttribute("user"));
        User opponent = room.getOpponentUser((User)session.getAttribute("user"));
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
                    <div class="progress" style="width: 100%" id="userHp">
                        <%= currentUser.getHp() %>
                    </div>
                </div>
                <div class="damage" id="userDamage"> <%= currentUser.getDamage() %> ед.ур.</div>
            </div>

            <button class="attack-button" id="attack" disabled>ATK</button>

            <div class="player">
                <div class="caption">Враг</div>
                <div class="name"><%= opponent.getUsername() %></div>
                <div class="progress-bar">
                    <div class="progress" style="width: 100%" id="opponentHp">
                        <%= opponent.getHp() %>
                    </div>
                </div>
                <div class="damage" id="opponentDamage"><%= opponent.getDamage() %> ед. ур.</div>

            </div>

        </div>

        <div class="log">
        </div>
    </div>

    <div id="resultsModal" class="modal">
        <div class="modal-content">
            <span class="close" id="closeResultsModal">&times;</span>
            <div class="result">
                Победа!
            </div>

            <div class="stats">
                <div class="stat">
                    <div class="name">Рейтинг</div>
                    <div class="value">+1</div>
                </div>

                <div class="stat">
                    <div class="name">Урон</div>
                    <div class="value">+1</div>
                </div>

                <div class="stat">
                    <div class="name">Здоровье</div>
                    <div class="value">+1</div>
                </div>
            </div>

            <div class="buttons-wrapper">
                <a href="<c:url value="/duels"/>" class="button">Дуэли</a>
                <a href="<c:url value="/"/>" class="button">Главная</a>
            </div>

        </div>
    </div>

    <div class="footer">page: 100ms, db: 5req (20 ms)</div>
</body>
</html>
