<%-- 
    Document   : index
    Created on : Sep 19, 2019, 10:31:32 AM
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
        <h1>Сетевая библиотека</h1>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <br>
        Для всех:<br>
        <c:if test="${user eq null}">
            <a href="showLogin">Войти</a><br>
        </c:if>
        <c:if test="${user ne null}">
            <a href="logout">Выйти</a><br>
        </c:if>
        <a href="newReader">Регистрация</a><br>
        <br>
        <a href="listBooks">Список книг</a><br>
        
        <c:if test="${user ne null && user.login eq 'admin'}">
            <br>
            Для администратора:<br>
            <a href="newBook">Добавить новую книгу</a><br>
            <a href="listReaders">Список читателей</a><br>
            <a href="listAllBooks">Список всех книг</a><br>
            <a href="takeOnBooks">Список выданных книг</a><br>
            <a href="showAdmin">Изменить роль пользователя</a><br>
        </c:if>
    </body>
</html>
