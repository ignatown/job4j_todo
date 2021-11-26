<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>TODO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<div style="text-align: center;">
<div class="container-fluid">
    <h3>Менеджер задач — ToDo</h3>
    <h6> Чтобы продолжить, зайдите в свою учетную запись или зарегистрируйтесь</h6>
</div>
</div>
<div class="container-fluid mt-3 mb-3">
    <div class="row border bg-light">
        <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Авторизация</a>
                </li>
            <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
                </li>
        </ul>
    </div>
</div>
</body>
</html>