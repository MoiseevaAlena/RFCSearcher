<%@ page import="java.util.List" %>
<%@ page import="ru.itpark.model.QueryModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Search</title>
    <%@include file="bootstrap-css.jsp" %>
</head>
<body>


<br>
<div align="center">
    <h2>Добро пожаловать на RFC Searcher!<h2>
        <button onclick="location.href='/search'" class="btn btn-primary mt-3">Начать поиск</button>
</div>

<%@include file="bootstrap-scripts.jsp" %>
</body>
</html>