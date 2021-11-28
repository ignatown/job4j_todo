<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

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
    <script type="text/javascript" src="scripts.js"></script>
</head>
<body>
<div style="text-align: center;">
<div class="container-fluid">
    <h2>Менеджер задач — ToDo</h2>
</div>
</div>
<div class="container-fluid mt-3 mb-3">
    <div class="row border bg-light">
        <ul class="nav">
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <b class="nav-link"><c:out value="${user.name}"/></b>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<div class="container-fluid">
    <form class="row g-3" action="<%=request.getContextPath()%>/task" method="post">
        <div class="mb-3">
            <label for="addTask" class="form-label">Введите текст новой задачи:</label>
            <textarea class="form-control" name="description" id="addTask" rows="3" ></textarea>
            <label for="categoryIds" class="form-label">Выберите категорию задачи:</label>
                <select class="form-select" name="categoryIds" id="categoryIds" multiple>
                    <c:forEach items="${allCategory}" var="categories">
                        <option value='<c:out value="${categories.id}"/>'>
                            <c:out value="${categories.name}" />
                        </option>
                    </c:forEach>
                </select>
            <button type="submit" class="btn btn-primary mt-3" onclick="return validate()">Добавить задачу</button>
        </div>
    </form>
</div>
<div class="container-fluid" id="itemsList">
    <div style="text-align: center;">
    <h4>Список задач</h4>
    </div>
    <form action="<%=request.getContextPath()%>/close" method="post">
        <ul class="list-group" id="itemsListUl">
            <c:forEach items="${tasks}" var="task">
                <li class="list-group-item">
                    <input class="form-check-input me-1" type="radio"  name="check" value='<c:out value="${task.id}"/>'
                           <c:if test="${task.done == true}">checked</c:if> >
                    <i><c:out value="${user.name}"/>:</i>  <c:out value="${task.description}"/>
                    <c:forEach items="${task.categoryList}" var="ct">
                      [<c:out value="${ct.name}"/>]
                    </c:forEach>
                </li>
            </c:forEach>
        </ul>
        <button type="submit" class="btn btn-primary mt-3">Закрыть задачу</button>
    </form>
</div>
<div class="container-fluid">
    <button type="submit" class="btn btn-primary mt-3" onclick="return showOffTask()">Актуальные задачи</button>
    <button type="submit" class="btn btn-primary mt-3" onclick="return showAllTask()">Все задачи</button>
</div>
</body>
</html>