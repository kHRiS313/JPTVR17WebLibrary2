<%-- 
    Document   : editBook
    Created on : Sep 26, 2019, 10:59:04 AM
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
        <h1>Редактирование атрибутов книги!</h1>
        <a href="index">Главная страница</a>
        <p>${info}</p>
        <form action="changeBook" method="POST">
            <input type="hidden" name="id" value="${book.id}">
            Название книги: <input type="text" name="name" value="${book.name}"><br>
            Автор книги: <input type="text" name="author" value="${book.author}"><br>
            Год издания: <input type="text" name="publishedYear"  value="${book.publishedYear}"><br>
            ISBN: <input type="text" name="isbn" value="${book.isbn}"><br>
            Количество экземпляров: <input type="text" name="countInLibrary" value="${book.countInLibrary}"><br>
            Доступность книги: <input type="checkbox" name="active" <c:if test='${book.active}'>checked</c:if>><br>
            <input type="submit" value="Сохранить изменения">
        </form>
    </body>
</html>
