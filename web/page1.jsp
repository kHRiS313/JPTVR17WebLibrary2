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
        <h1>Привет из вервлета!</h1>
        <p>${info}</p>
        <p>page1.jsp</p>
        <a href="page1?num=1">Один</a>
        <a href="page1?num=2">Два</a>
        <p>В нажали на ${num}</p>
    </body>
</html>
