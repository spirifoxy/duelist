<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Дуэли</title>
</head>
<body>
    <div>
        <div>
            Рейтинг
        </div>
        <form method="POST" action="<c:url value="/duels"/>">
            <button>Начать дуэль</button>
        </form>
    </div>
</body>
</html>
