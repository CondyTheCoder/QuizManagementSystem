<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Quiz Result</title>
</head>
<body>

<h2>Quiz Results</h2>

<!-- Displaying quiz details -->
<h3>Quiz Name: ${quiz.name}</h3>
<p>User's Full Name: ${user.firstname} ${user.lastname}</p> <!-- Replace with actual property names -->
<p>Start Time: ${quiz.time_start}</p>
<p>End Time: ${quiz.time_end}</p>
<p>Result: ${quiz.result}</p>

<hr>

<!-- Iterating over each question -->
<c:forEach items="${questions}" var="question" varStatus="status">
    <h4>Question: ${question.description}</h4> <!-- Replace 'text' with the actual property name -->

    <!-- Displaying the user's choice -->
    <p>Selected Choice:
        <c:choose>
            <c:when test="${status.index == 0}">${quizDetail.choice1}</c:when>
            <c:when test="${status.index == 1}">${quizDetail.choice2}</c:when>
            <c:when test="${status.index == 2}">${quizDetail.choice3}</c:when>
            <c:when test="${status.index == 3}">${quizDetail.choice4}</c:when>
            <c:when test="${status.index == 4}">${quizDetail.choice5}</c:when>
        </c:choose>
    </p>

    <!-- Displaying the correct answer -->
    <p>Correct Answer:
        <c:choose>
            <c:when test="${status.index == 0}">${quizDetail.correctChoice1}</c:when>
            <c:when test="${status.index == 1}">${quizDetail.correctChoice2}</c:when>
            <c:when test="${status.index == 2}">${quizDetail.correctChoice3}</c:when>
            <c:when test="${status.index == 3}">${quizDetail.correctChoice4}</c:when>
            <c:when test="${status.index == 4}">${quizDetail.correctChoice5}</c:when>
        </c:choose>
    </p>
</c:forEach>

<hr>

<!-- Link to take another quiz -->
<a href="/home">Take Another Quiz</a>

</body>
</html>