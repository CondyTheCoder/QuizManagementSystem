<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<html>
<head>
    <title>Home</title>
</head>

<body>
<%-- div is for grouping items --%>
<div>
    <h1>Welcome to Your Home Page</h1>
    <!-- Display user information here -->

    <!-- Quiz Categories Section -->

    <h2>Quiz Categories</h2>
    <p>There are ${categories.size()} categories in the bank.</p>
    <form method="post" action="/home">
        <ul>
            <c:forEach items="${categories}" var="category" varStatus="loop">
                <div>
                    <input type="radio"
                           name="selectedCategoryId"
                           value="${category.category_id}"/>
                        ${category.name}
                </div>
            </c:forEach>
        </ul>
        <input type="submit" value="Start Quiz" class="btn btn-primary"/>
    </form>

    <!-- Recent Quiz Result Table -->
    <h2>Recent Quiz Results</h2>
    <table border="1">
        <tr>
            <th>Quiz Name</th>
            <th>Date Taken</th>
            <th>Result</th>
        </tr>
        <c:forEach items="${quizzes}" var="quiz">
            <tr>
                <td>${quiz.name}</td>
                <td>${quiz.date}</td>
                <td><a href="/quiz-result/${quiz.quiz_id}">View Result</a></td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>

</html>