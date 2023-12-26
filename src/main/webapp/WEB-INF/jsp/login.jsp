<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>

<body>
<%-- div is for grouping items --%>
<div>
    <form method="post" action="/login">
        <div>
            <label>Email</label>
            <input type="email" name="email">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <button type="submit">Submit</button>
    </form>

    <!-- Registration Link -->
    <p>Don't have an account? <a href="/register">Register here</a>.</p>
</div>
</body>

</html>
