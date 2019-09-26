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
        <form action="addReader" method="POST">
            Имя: <input type="text" name="name"><br>
            Фамилия: <input type="text" name="surname"><br>
            Телефон: <input type="text" name="phone"><br>
            <input type="submit" value="Добавить читателя">
        </form>
        <c:if test="${reader ne null}">
            <p>Добавлен новый пользователь: ${reader.name} ${reader.surname}</p>
        </c:if>
        
    </body>
</html>
