<%-- 
    Document   : takeBook
    Created on : Sep 19, 2019, 10:55:07 AM
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
        <h1>Выдать книгу</h1>
        <a href="index.jsp">Главная страница</a>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <form action="doTakeBook" method="POST">
            
            Список книг: 
            <select name="bookId">
                <c:forEach var="book" items="${listBooks}">
                    <option value="${book.id}">${book.name} ${book.author}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Выдать книгу">
        </form>
    </body>
</html>
