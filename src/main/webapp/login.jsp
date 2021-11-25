<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>TODO - Авторизация</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script type="text/javascript" src="scripts.js"></script>
</head>
<body>
<div class="container-fluid">
    <div style="text-align: center;">
    <h3>Менеджер задач — ToDo</h3>
    <h4>Авторизация</h4>
    </div>
</div>

<div class="container-fluid">

    <div class="card" style="width: 100%">
        <div class="card-header">
            <a class="card-header-link" href="<%=request.getContextPath()%>/reg">Регистрация</a>
        </div>
        <div class="card-body">
            <form action="<%=request.getContextPath()%>/login" method="post">
                <div class="form-group col-3 p-1">
                    <label>Почта</label>
                    <input type="text" class="form-control" name="email" title="Введите e-mail">
                </div>
                <div class="form-group col-3 p-1">
                    <label>Пароль</label>
                    <input type="text" class="form-control" name="password" title="Введите пароль">
                </div>
                <div class="form-group col-3 p-1 mt-2">
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Войти</button>
                </div>
                <c:if test="${not empty error}">
                    <div style="color:#ff0000; font-weight: bold; margin: 30px 0;">
                            ${error}
                    </div>
                </c:if>
            </form>
        </div>
    </div>

</div>
</body>
</html>