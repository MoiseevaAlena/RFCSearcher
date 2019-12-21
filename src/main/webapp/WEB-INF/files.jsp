<%@ page import="java.util.List" %>
<%@ page import="ru.itpark.model.QueryModel" %>
<%@ page import="java.nio.file.Path" %>
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


<div class="container">

    <div class="row">
        <div class="col">
        </div>
        <div class="col">
            <br>
            <br>
            <button onclick="location.href='/search'" class="btn btn-primary mt-3">Вернуться на главную страницу</button>
        </div>
        <div class="col">
        </div>
    </div>

    <h3>Список файлов, по которым осуществляется поиск:</h3>
    <%for (Path path : (List<Path>) request.getAttribute("listFile")) {%>
    <p><%=path%>
    </p>
    <%}%>

</div>

<%@include file="bootstrap-scripts.jsp" %>
</body>
</html>