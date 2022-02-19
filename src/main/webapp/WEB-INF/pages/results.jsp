<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="dayResults" type="java.util.List" scope="request"/>

<html>
<head>
    <title>Results</title>
    <style>
        table {
            border-collapse: collapse;
            border: 1px solid black;
        }

        th, td {
            border: 1px solid black;
            padding: 5px;
        }
    </style>
</head>
<body>
    <table>
        <caption>Results of competitions</caption>
        <tr>
            <th>day</th>
            <th colspan="6">result</th>
        </tr>
        <c:forEach var="res" items="${dayResults}">
            <tr>
                <td>${res.day}</td>
                <c:forEach var="r" items="${res.results}">
                    <td>${r.key}</td>
                    <td>${r.value}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <table>
        <caption>Statistics</caption>
        <tr>
            <th>max</th>
            <th>mim</th>
            <th>average</th>
            <th>winner</th>
        </tr>
        <c:forEach var="stat" items="${dayStats}">
            <tr>
                <td>${stat.max}</td>
                <td>${stat.min}</td>
                <td>${stat.avg}</td>
                <td>${stat.winner}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
