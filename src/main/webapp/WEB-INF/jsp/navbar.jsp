<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
    <ul>
        <c:if test="${not onLoginPage}">
            <li><a href="/home">Home</a></li>
        </c:if>
        <%-- Display "Login" or "Logout" based on user login status --%>
        <c:choose>
            <c:when test="${empty user}">
                <li><a href="/login">Login</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="/logout">Logout</a></li>
            </c:otherwise>
        </c:choose>
        <%-- Hide "Register" when the user is logged in --%>
        <c:if test="${empty user}">
            <li><a href="/register">Register</a></li>
        </c:if>
        <li><a href="/contactus">Contact Us</a></li>
    </ul>
</nav>