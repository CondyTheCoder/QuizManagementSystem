<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHeader.jsp" %>
<html>
<head>
    <title>Question Management</title>
    <!-- Add CSS and JavaScript as needed -->
</head>
<body>
<h2>Question Management</h2>

<!-- Button to Add New Question -->
<a href="/add-question">Add New Question</a>

<!-- Questions Table -->
<table border="1">
    <tr>
        <th>CategoryID</th>
        <th>Description</th>
        <th>Is Active</th>
        <!-- Add other necessary columns here -->
        <th>Actions</th>
    </tr>
    <c:forEach items="${questions}" var="question">
        <tr>
            <td>${question.category_id}</td>
            <td>${question.description}</td>
            <td>${question.is_active()}</td>
            <!-- Display other details of the question -->
            <td>
                <!-- Edit Button -->
                <a href="/edit-question/${question.question_id}">Edit</a>
                <!-- Activate/Suspend Button -->
                <form action="/toggle-question-status" method="post">
                    <input type="hidden" name="questionId" value="${question.question_id}" />
                    <input type="submit" name="action" value="${question.is_active() ? 'Suspend' : 'Activate'}" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>