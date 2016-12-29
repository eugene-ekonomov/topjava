<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table>
    <tr>
        <th>Дата</th>
        <th>Приём пищи</th>
        <th>Калории</th>
        <th>Превышение</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr>
            <c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}" />
            <fmt:parseDate value="${ cleanedDateTime }" pattern="yyyy-MM-dd HH:mm" var="parsedDateTime" type="both" />
            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${parsedDateTime}" /></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.exceed}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
