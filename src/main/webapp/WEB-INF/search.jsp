<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Главная</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%@include file="bootstrap-css.jsp" %>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col">
            <h1 align="center">RFC Searcher</h1>
            <br>
            <form action="<%= request.getContextPath() %>" method="post" enctype="multipart/form-data" class="nt-3">
                <div class="form-group">
                    <input type="hidden" name="action" value="search">
                </div>
                <dif class="form-group">
                    <input type="text" id="name" name="name" placeholder="Введите поисковый запрос" class="form-control" required>
                </dif>

                <button type="submit" class="btn btn-primary mt-3">Найти</button>
            </form>

            <br>
            <h2>Загрузить файл</h2>
            <form action="<%= request.getContextPath() %>" method="post" enctype="multipart/form-data" class="nt-3">

                    <input type="hidden" name="action" value="saveFile">

                    <input type="file" name="file" class="form-control-file" accept="text/plain">

                <br>
                    <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Загрузить</button>

            </form>

            <br>
            <br>
            <div align="center">
                <button onclick="location.href='/results'" class="btn btn-primary mt-3">Перейти на страницу результатов</button>
            </div>



        </div>
    </div>
</div>
<%@include file="bootstrap-scripts.jsp" %>
</body>
</html>




