<%-- 
    Document   : page1
    Created on : Sep 16, 2019, 10:04:33 AM
    Author     : melnikov
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Привет из сервлета!</h1>
        <p>${info}</p>
        <p>page2.jsp</p>
        <form action="page2" method="POST">
            Номер 1: <input type="text" name="num1" value="${num1}"><br>
            Номер 2: <input type="text" name="num2" value="${num2}"><br>
            Операция: <select name="znak">
                <option value="+" <c:if test="${znak eq '+'}">selected </c:if>>+</option>
                <option value="-" <c:if test="${znak eq '-'}">selected </c:if>>-</option>
                <option value="*" <c:if test="${znak eq '*'}">selected</c:if>>*</option>
                <option value="/" <c:if test="${znak eq '/'}">selected</c:if>>/</option>
            </select>
            <input type="submit" value="Отправить на сервер">
            
        </form>
        <p>результат: <fmt:formatNumber value="${res}" pattern="###.###"/></p>
                
    </body>
</html>
