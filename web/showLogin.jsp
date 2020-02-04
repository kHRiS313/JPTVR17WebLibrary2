<%-- 
    Document   : showLogin
    Created on : Oct 4, 2019, 12:49:32 PM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="login" method="POST">
    <div class="card col-6 text-center offset-3" style="width: 24 rem; margin-top: 50px">
      <div class="card-body">
            <h5 class="card-title">Аутентификация</h5>
            <p class="card-text">${info}</p>
            <div class="form-group">
            <label for="login">Логин:</label>
            <input type="text" class="form-control" name="login" id="login">

            <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" id="password">
            <br>

            <input class="btn btn-success" type="submit" value="Войти">
          </div>
        </div>
      </div>
        <div class="text-center" style="margin-top:10px;margin-bottom: 3%"> Нет учетной записи? <a href="newReader">Зарегистрироваться</a><br> </div>
        </div>
</form>
