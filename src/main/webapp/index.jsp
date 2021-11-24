<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>TODO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>

<body>
<div style="text-align: center;">
<div class="container-fluid">
    <h3>Менеджер задач — ToDo</h3>
</div>
</div>
</div>
<div class="container-fluid">
    <form class="row g-3">
        <div class="mb-3">
            <h4><label for="addNew" class="form-label">Введите текст новой задачи: </label> </h4>
            <textarea class="form-control" id="addNew" rows="3"></textarea>
            </div>
            <div style="text-align: center;">
                  <button type="submit" class="btn btn-primary" onclick="return newItem()">Добавить задачу</button>
             </div>
    </form>
</div>
<div class="container-fluid" id="itemsList">
    <br/>
    <h4> Список задач: </h4>
    <ul class="list-group">
    </ul>
    <br/>
    <div class="container-fluid">
        <button type="button" class="btn btn-primary btn-sm" onclick="loadOff()">Показать актуальные задачи</button>
        <button type="button" class="btn btn-primary btn-sm" onclick="loadAll()">Показать все задачи</button>
       <div style="text-align: center;">
        <button type="submit" class="btn btn-primary" onclick="wasDone()">Закрыть выбранные задачи</button>
       </div>
    </div>
</div>

<script type="text/javascript" src="scripts.js"></script>
<script>
    $(document).ready(loadOff());
</script>
</body>
</html>