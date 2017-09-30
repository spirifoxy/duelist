<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
    <form method="POST" action="<c:url value="/login"/>">
        <input placeholder="Имя пользователя" name="username" maxlength="20" type="text" required>
        <input placeholder="Пароль" name="password" maxlength="40" type="password" required>
        <button>Войти</button>
    </form>
</body>
</html>
