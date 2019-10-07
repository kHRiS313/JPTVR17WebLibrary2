<%-- 
    Document   : returnBook
    Created on : Sep 19, 2019, 10:55:35 AM
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
        <h1>Возврат книги</h1>
        <a href="index.jsp">Главная страница</a>
        <p>${info}</p>
        <p>Вошедший пользователь: ${user.login}</p>
        <form action="doReturnBook" method="POST">
            <select name="historyId">
                <c:forEach var="history" items="${listHistories}">
                    <option value="${history.id}">${history.reader.name} ${history.reader.surname} читает книгу ${history.book.name}
                </c:forEach>
            </select><br>
            <input type="submit" value="Вернуть книгу">
        </form>
    </body>
</html>
