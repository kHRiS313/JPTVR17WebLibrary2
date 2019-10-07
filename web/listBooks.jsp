<%-- 
    Document   : listBooks
    Created on : Sep 19, 2019, 10:54:00 AM
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
        <h1>Список книг библиотеки</h1>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <a href="index.jsp">Главная страница</a>
        <ul>
            <c:forEach var="book" items="${listBooks}">
                <li>
                    Название: ${book.name} <a href="showBook?id=${book.id}">Показать</a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
