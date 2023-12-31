<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHeader.jsp" %>
<html>
<head>
    <title>User Management</title>
    <!-- Add CSS and JavaScript as needed -->
</head>
<body>
<h2>User Management</h2>
<table border="1">
    <tr>
        <th>Full Name</th>
        <th>Email</th>
        <th>Status</th>
        <th>Admin</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.firstname} ${user.lastname}</td>
            <td>${user.email}</td>
            <td>${user.is_active()}</td>
            <td>${user.is_admin()}</td>
            <td>
                <form action="/toggleUserStatus" method="post">
                    <input type="hidden" name="userId" value="${user.id}" />
                    <c:choose>
                        <c:when test="${user.is_active() == true}">
                            <input type="submit" name="action" value="Suspend" />
                        </c:when>
                        <c:otherwise>
                            <input type="submit" name="action" value="Activate" />
                        </c:otherwise>
                    </c:choose>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>