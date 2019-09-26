<%-- 
    Document   : showBook
    Created on : Sep 26, 2019, 10:45:43 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Книга</h1>
        Название книги: ${book.name}<br>
        Название книги: ${book.author}<br>
        Год издания книги: ${book.publishedYear}<br>
        
        <a href="editBook?id=${book.id}">Изменить</a>
    </body>
</html>
