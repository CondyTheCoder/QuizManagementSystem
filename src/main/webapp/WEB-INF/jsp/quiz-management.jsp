<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHeader.jsp" %>
<html>
<head>
    <title>Quiz Result Management</title>
    <!-- Add CSS and JavaScript as needed -->
</head>
<body>

<!-- Filter Form -->
<form action="/filter" method="get">
    <label for="categoryFilter">Filter by Category:</label>
    <select name="categoryFilter" id="categoryFilter">
        <option value="">--Select Category--</option>
        <c:forEach items="${allCategories}" var="category">
            <option value="${category.category_id}">${category.name}</option>
        </c:forEach>
    </select>

    <label for="userFilter">Filter by User:</label>
    <select name="userFilter" id="userFilter">
        <option value="">--Select User--</option>
        <c:forEach items="${allUsers}" var="user">
            <option value="${user.id}">${user.firstname} ${user.lastname}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Filter Results" />
</form>

<!-- Quiz Results Table -->
<table border="1">
    <tr>
        <th>Taken Time</th>
        <th>Category</th>
        <th>User Full Name</th>
        <th>Number of Questions</th>
        <th>Score</th>
        <th>Details</th>
    </tr>
    <c:forEach items="${quizzes}" var="quizResult" varStatus="status">
        <tr>
            <td>${quizResult.time_start}</td>
            <td>${categories[status.index]}</td>
            <td>${users[status.index].firstname} ${users[status.index].lastname}</td>
            <td>${5}</td>
            <td>${quizResult.result}</td>
            <td><a href="/quiz-result/${quizResult.quiz_id}">View Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>