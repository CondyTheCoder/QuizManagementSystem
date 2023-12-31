<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHeader.jsp" %>
<html>
<head>
    <title>Contact Us Management</title>
    <!-- Add any required CSS or JS links here -->
</head>
<body>
<h2>Contact Us Messages</h2>

<!-- Table for displaying contact us messages -->
<table border="1">
    <tr>
        <th>Subject</th>
        <th>Email Address</th>
        <th>Time</th>
        <th>Message Content</th>
    </tr>

    <!-- Loop through each contact us message and display its details -->
    <c:forEach items="${contactUsMessages}" var="message">
        <tr>
            <td>${message.subject}</td>
            <td>${message.email}</td>
            <td>${message.time}</td>
            <td>${message.message}</td>
        </tr>
    </c:forEach>
</table>

<a href="/admin-home">Back to Admin Home</a>
</body>
</html>