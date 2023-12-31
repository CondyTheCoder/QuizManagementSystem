<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Edit Question</title>
    <!-- Add any required CSS or JS links here -->
</head>
<body>
<h2>Edit Question</h2>

<!-- Form for editing the question -->
<form action="/update-question" method="post">
    <!-- Hidden field for question ID -->
    <input type="hidden" name="questionId" value="${question.question_id}" />

    <!-- Field for editing the question description -->
    <label for="description">Question Description:</label>
    <textarea id="description" name="description" required>${question.description}</textarea>

    <h3>Choices</h3>
    <c:forEach items="${choices}" var="choice" varStatus="status">
        <div>
            <!-- Field for editing choice description -->
            <label for="choice${status.index}">Choice ${status.index + 1}:</label>
            <input type="text" id="choice${status.index}" name="choices[${status.index}]" value="${choice.description}" required />

            <!-- Checkbox for marking choice as correct -->
            <input type="checkbox" id="correct${status.index}" name="correctAnswer" value="${status.index + 1}" ${choice.is_correct() ? 'checked' : ''}/>
            <label for="correct${status.index}">Correct</label>
        </div>
    </c:forEach>

    <!-- Checkbox for setting the question as active or not -->
    <div>
        <input type="checkbox" id="isActive" name="isActive" ${question.is_active() ? 'checked' : ''}/>
        <label for="isActive">Is Active</label>
    </div>

    <!-- Submit button -->
    <input type="submit" value="Update Question" />
</form>

<a href="/question-management">Back to Question Management</a>
</body>
</html>
