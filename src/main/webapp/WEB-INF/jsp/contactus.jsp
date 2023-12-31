<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Contact Us</title>
</head>
<body>
<div>
    <h1>Contact Us</h1>
    <form method="post" action="/send-message">
        <div>
            <label>Subject:</label>
            <input type="text" name="subject">
        </div>
        <div>
            <label>Email Address:</label>
            <input type="email" name="email">
        </div>
        <div>
            <label>Message:</label>
            <textarea name="message" rows="5" cols="40"></textarea>
        </div>
        <button type="submit">Send Message</button>
    </form>
</div>
</body>
</html>