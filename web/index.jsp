<%-- 
    Document   : index
    Created on : Sep 19, 2019, 10:31:32 AM
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
        <h1>Сетевая библиотека</h1>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <br>
        Для всех:<br>
        <a href="showLogin">Войти</a><br>
        <a href="logout">Выйти</a><br>
        <a href="newReader">Регистрация</a><br>
        <br>
        Для вошедших пользователей:<br>
        <a href="listBooks">Список книг</a><br>
        
        <br>
        Для администратора:<br>
        <a href="newBook">Добавить новую книгу</a><br>
        <a href="listReaders">Список читателей</a><br>
        <a href="listAllBooks">Список всех книг</a><br>
        <a href="takeOnBooks">Список выданных книг</a><br>
    </body>
</html>
