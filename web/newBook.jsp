<%-- 
    Document   : newBook
    Created on : Sep 19, 2019, 10:52:18 AM
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
        <h1>Добавление новой книги!</h1>
        <form action="addBook" method="POST">
            Название книги: <input type="text" name="name"><br>
            Автор книги: <input type="text" name="author"><br>
            Год издания: <input type="text" name="publishedYear"><br>
            ISBN: <input type="text" name="isbn"><br>
            Количество экземпляров: <input type="text" name="quantity"><br>
            <input type="submit" value="Добавить книгу">
        </form>
        <p>Добавлена книга: ${book}</p>
    </body>
</html>
