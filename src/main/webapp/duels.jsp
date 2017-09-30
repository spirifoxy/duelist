<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Дуэли</title>
    <script src="<c:url value="/js/script.js" />"></script>
</head>
<body>
    <div>
        <div>
            Рейтинг
        </div>
        <form method="POST" action="<c:url value="/duels"/>">
            <button id="startDuel">Начать дуэль</button>
        </form>
    </div>
</body>
</html>
