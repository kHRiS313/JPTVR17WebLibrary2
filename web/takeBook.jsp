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
        <p>${info}</p>
        <form action="doTakeBook" method="POST">
            Список читателей:
            <select name="readerId">
                <c:forEach var="reader" items="${listReaders}">
                    <option value="${reader.id}">${reader.name} ${reader.surname}</option>
                </c:forEach>
            </select>
            <br>
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
