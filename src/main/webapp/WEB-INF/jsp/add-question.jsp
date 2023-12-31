<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add New Question</title>
    <!-- Add any required CSS or JS links here -->
</head>
<body>
<h2>Add New Question</h2>

<!-- Form for adding a new question -->
<form action="/save-question" method="post">

    <!-- Field for entering the question description -->
    <label for="description">Question Description:</label>
    <textarea id="description" name="description" required></textarea>

    <!-- Dropdown for selecting the category -->
    <label for="category_id">Category:</label>
    <select id="category_id" name="category_id">
        <c:forEach items="${categories}" var="category">
            <option value="${category.category_id}">${category.name}</option>
        </c:forEach>
    </select>

    <h3>Choices</h3>
    <!-- Fields for entering choices. Adjust the number of choices as needed -->
    <c:forEach begin="1" end="5" varStatus="status">
        <div>
            <label for="choice${status.index}">Choice ${status.index}:</label>
            <input type="text" id="choice${status.index}" name="choices[${status.index - 1}]" required />
        </div>
    </c:forEach>

    <!-- Field for specifying the correct answer -->
    <label for="correctAnswer">Correct Answer (Choice number):</label>
    <input type="number" id="correctAnswer" name="correctAnswer" min="1" max="5" required />

    <!-- Checkbox for setting the question as active or not -->
    <div>
        <input type="checkbox" id="isActive" name="isActive" checked />
        <label for="isActive">Is Active</label>
    </div>

    <!-- Submit button -->
    <input type="submit" value="Add Question" />
</form>

<a href="/question-management">Back to Question Management</a>
</body>
</html>
