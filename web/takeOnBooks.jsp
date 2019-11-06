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
        <title>Список выданных книг библиотеки</title>
    </head>
    <body>
        <h1>Список выданных книг библиотеки</h1>
        <p>${info}</p>
        
        <a href="index">Главная страница</a>
        <ul>
            <c:forEach var="history" items="${takeOnBooks}">
                <li>
                   Книгу "${history.book.name}" читает ${history.reader.name} ${history.reader.surname} <a href="showBook?id=${history.book.id}">Показать</a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
