<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/login.css" />"/>
</head>
<body>
    <div class="container">
        <form class="login-form" method="POST" action="<c:url value="/login"/>">
            <input placeholder="Имя пользователя" name="username" maxlength="20" type="text" required>
            <input placeholder="Пароль" name="password" maxlength="40" type="password" required>
            <button>Войти</button>
        </form>
    </div>
    <div class="footer">page: 100ms, db: 5req (20 ms)</div>
</body>
</html>
