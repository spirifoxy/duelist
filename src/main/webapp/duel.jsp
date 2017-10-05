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

        User currentRoomUser = room.getCurrentUser(currentUser);
        User roomOpponent = room.getOpponentUser(currentUser);
    %>

    <title>Дуэль с  <%= roomOpponent.getUsername() %></title>
    <script src="<c:url value="/js/script.js" />"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/duel.css" />"/>
</head>
<body>

    <div class="container">
        <div>Комната № <%= room.getId() %></div>
        <div class="opponents">
            <div class="player">
                <div class="caption">Вы</div>
                <div class="name"><%= currentRoomUser.getUsername() %></div>
                <div class="progress-bar">
                    <div class="progress" style="width:
                        <%= currentUser.getHp() > 0 ? currentRoomUser.getHp() * 100 / currentUser.getHp() : 0 %>%" id="userHp">
                        <%= currentRoomUser.getHp() %>
                    </div>
                </div>
                <div class="damage" id="userDamage"> <%= currentRoomUser.getDamage() %> ед.ур.</div>
            </div>

            <button class="attack-button" id="attack" disabled>ATK</button>

            <div class="player">
                <div class="caption">Враг</div>
                <div class="name"><%= roomOpponent.getUsername() %></div>
                <div class="progress-bar">
                    <div class="progress" style="width:
                        <%= roomOpponent.getHp() > 0 ? roomOpponent.getHp() * 100 / opponent.getHp() : 0 %>%" id="opponentHp">
                        <%= roomOpponent.getHp() %>
                    </div>
                </div>
                <div class="damage" id="opponentDamage"><%= roomOpponent.getDamage() %> ед. ур.</div>

            </div>

        </div>

        <div class="log">
        </div>
    </div>

    <div id="resultsModal" class="modal">
        <div class="modal-content">
            <span class="close" id="closeResultsModal">&times;</span>
            <div class="container">
                <div class="result">
                    <% if(currentUser.getStatus() == User.UserStatus.WINNER) %> Победа!
                    <% if(currentUser.getStatus() == User.UserStatus.LOSER) %> Поражение
                </div>
                <a href="<c:url value="/result"/>" class="button">К результатам</a>
            </div>
        </div>
    </div>

    <div class="footer">page: 100ms, db: 5req (20 ms)</div>
</body>
</html>
