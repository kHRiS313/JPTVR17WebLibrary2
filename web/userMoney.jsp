<%-- 
    Document   : userMoney
    Created on : Feb 4, 2020, 9:02:52 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Кол-во денег в кошельке</title>
    </head>
    <body>
        <h1>Кол-во денег в кошельке</h1>
        <p>$(info)</p>
        
        <a href="index">Главная страница</a>
        Логин: ${user.login}
        
        
        
        
         <h1>Книга</h1>
        <a href="index">Главная страница</a><br>
        Название книги: ${book.name}<br>
        Название книги: ${book.author}<br>
        Год издания книги: ${book.publishedYear}<br>
        Количество книг в наличии: ${book.countInLibrary}<br>
        <c:if test="${userRole eq 'MANAGER' || userRole eq 'ADMIN'}">
            <a href="editBook?id=${book.id}">Изменить</a><br>
        </c:if>
        <c:if test="${userRole eq 'USER' || userRole eq 'MANAGER' || userRole eq 'ADMIN'}">     
            <a href="doTakeBook?bookId=${book.id}">Почитать (за деньги)</a>
        </c:if>
    </body>
    </body>
</html>
