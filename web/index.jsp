<%-- 
    Document   : index
    Created on : Sep 19, 2019, 10:31:32 AM
    Author     : Melnikov
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h1>Сетевая библиотека</h1>
        <p>${info}</p>
        
        <br>
        Для всех:<br>
        <c:if test="${userRole eq null}">
            <a href="showLogin">Войти</a><br>
        </c:if>
        <c:if test="${userRole ne null}">
            <a href="logout">Выйти</a><br>
        </c:if>
        <a href="newReader">Регистрация</a><br>
        <br>
        <a href="listBooks">Список книг</a><br>
        
        <c:if test="${userRole eq 'MANAGER' || userRole eq 'ADMIN'}">
            <br>
            Для администратора:<br>
            <a href="newBook">Добавить новую книгу</a><br>
            <a href="listReaders">Список читателей</a><br>
            <a href="listAllBooks">Список всех книг</a><br>
            <a href="takeOnBooks">Список выданных книг</a><br>
            
        </c:if>
        <c:if test="${userRole eq 'ADMIN'}">
            <a href="showAdmin">Изменить роль пользователя</a><br>
        </c:if>
        