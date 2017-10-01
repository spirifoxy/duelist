<%@ page import="duelist.spirifoxy.com.github.model.Room" %>
<%@ page import="duelist.spirifoxy.com.github.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подготовка к бою</title>

    <%
        Room room = (Room)request.getAttribute("room");
        User currentUser = (User)session.getAttribute("user");
    %>

    <% room.getOpponentUser(currentUser); %>

    <meta http-equiv="Refresh" content="<%= room.getTimeToStart() %>; %>;url=next_page.jsp">

</head>
<body>
Ожидайте...
</body>
</html>
