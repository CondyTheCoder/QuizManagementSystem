<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHeader.jsp" %>
<html>
<head>
    <title>Admin Home</title>
    <style>
        .admin-button {
            display: block;
            margin: 10px 0;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div>
    <h1>Admin Dashboard</h1>

    <!-- Link to User Management Page -->
    <a href="/user-management" class="admin-button">User Management</a>

    <!-- Link to Quiz Result Management Page -->
    <a href="/quiz-management" class="admin-button">Quiz Result Management</a>

    <!-- Link to Question Management Page -->
    <a href="/question-management" class="admin-button">Question Management</a>

    <!-- Link to Contact Us Management Page -->
    <a href="/contact-management" class="admin-button">Contact Us Management</a>
</div>
</body>
</html>