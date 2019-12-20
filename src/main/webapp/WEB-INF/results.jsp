<%@ page import="java.util.List" %>
<%@ page import="javax.management.Query" %>
<%@ page import="ru.itpark.model.QueryModel" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.nio.file.Path" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Результаты поиска</title>
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


    <div class="row">
        <div class="col">
            <h2>Результаты поиска</h2>
            <%--
                            <%
                                List<Search> result = (List<Search>) request.getAttribute("listSearch");
                                if (result != null && !result.isEmpty()) {
                                    out.println("<ui>");
                                    for (Search s : result) {
                                        out.println("<li>" + "id запроса: " + s.getId() + "\n" +  " Поисковый запрос: " + s.getSearchLine() + " Статус: " + s.getStatus() + "</li>");
                                    }
                                    out.println("</ui>");
                                } else out.println("<p>There are no users yet!</p>");
                                %>
                                --%>
            <%List<QueryModel> result = (List<QueryModel>) request.getAttribute("listSearch");%>
             <%if (result != null && !result.isEmpty()) { %>

                    <table class="table">
                        <thead>
                    <tr>
                    <th scope="col">Поисковый запрос</th>
                    <th scope="col">Статус задачи</th>
                    <th scope="col">Скачать</th>
                    </tr>
                    </thead>
                        <% for (QueryModel s : result) {%>
                        <tr>
                            <td><%=s.getQuery()%></td>
                            <td><%=s.getStatus().getTitle()%></td>
                            <% if (s.getStatus().getTitle().contains("Выполнено")) {%>
                            <td>
                                <% String nameFile = s.getQuery() + ".txt"; %>
                            <a href = "<%= request.getContextPath()%>/download/<%=s.getId()%>" download="<%=nameFile%>">Скачать результат</a>
                            </td>
                       <%} else {%>
                        <td></td>
                            <% }%>
                        </tr>
                        <% }%>
                    </table>
            <% }%>
        </div>
    </div>

</div>

<%@include file="bootstrap-scripts.jsp" %>
</body>
</html>