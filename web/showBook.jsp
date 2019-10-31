<%-- 
    Document   : showBook
    Created on : Sep 26, 2019, 10:45:43 AM
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
        <h1>Книга</h1>
        <a href="index.jsp">Главная страница</a><br>
        Название книги: ${book.name}<br>
        Название книги: ${book.author}<br>
        Год издания книги: ${book.publishedYear}<br>
        Количество книг в наличии: ${book.countInLibrary}<br>
        <c:if test="${user ne null && user.login eq 'admin'}">
            <a href="editBook?id=${book.id}">Изменить</a><br>
        </c:if>
        <c:if test="${user ne null}">     
            <a href="doTakeBook?bookId=${book.id}">Почитать (за деньги)</a>
        </c:if>
    </body>
</html>
