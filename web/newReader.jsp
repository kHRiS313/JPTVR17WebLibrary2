<%-- 
    Document   : newReader
    Created on : Sep 19, 2019, 10:53:23 AM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Новый читатель!</h1>
        <a href="index.jsp">Главная страница</a>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <form action="addReader" method="POST">
            Имя: <input type="text" name="name"><br>
            Фамилия: <input type="text" name="surname"><br>
            Телефон: <input type="text" name="phone"><br>
            Login: <input type="text" name="login"><br>
            Пароль: <input type="password" name="password1"><br>
            Повторить пароль: <input type="password" name="password2"><br>
            <input type="submit" value="Добавить читателя">
        </form>
        <c:if test="${reader ne null}">
            
        </c:if>
        
    </body>
</html>
