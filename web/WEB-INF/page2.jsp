<%-- 
    Document   : page1
    Created on : Sep 16, 2019, 10:04:33 AM
    Author     : melnikov
--%>

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
            Номер 1: <input type="text" name="num1"><br>
            Номер 2: <input type="text" name="num2"><br>
            Операция: <select name="znak">
                <option value="+">+</option>
                <option value="-">-</option>
                <option value="*">*</option>
                <option value="/">/</option>
            </select>
            <input type="submit" value="Отправить на сервер">
            
        </form>
        <p>num 1 = ${num1}</p>
        <p>num 2 = ${num2}</p>
        <p>результат ${res}</p>
    </body>
</html>
