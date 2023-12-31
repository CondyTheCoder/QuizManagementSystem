<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ include file="header.jsp" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Quiz</title>--%>
<%--</head>--%>

<%--<body>--%>
<%--<div>--%>

<%--    <form method="post" action="/quiz">--%>

<%--        &lt;%&ndash; Question description &ndash;%&gt;--%>
<%--        <p>${question.description}</p>--%>

<%--        &lt;%&ndash; Dynamically render the choices &ndash;%&gt;--%>
<%--        <c:forEach items="${question.choices}" var="choice" varStatus="loop">--%>
<%--            <div>--%>
<%--                <input type="radio"--%>
<%--                       name="selectedChoiceId"--%>
<%--                       value="${choice.id}"/>--%>
<%--                ${choice.description}--%>
<%--            </div>--%>
<%--        </c:forEach>--%>

<%--        &lt;%&ndash; Button to submit quiz &ndash;%&gt;--%>
<%--        <button type="submit">submit</button>--%>

<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz</title>
</head>

<body>
<div>

    <form method="post" action="/quiz">
    <c:forEach items="${questions}" var="question" varStatus="loop">
        <p>${question.description}</p>
        <c:forEach items="${question.choices}" var="choice" varStatus="choiceLoop">
            <div>
                <input type="radio"

                       name="selectedChoice_${question.question_id}"
<%--                   name="selectedChoiceIds[${loop.index}]"--%>
                       value="${choice.choice_id}"/>
                    ${choice.description}
            </div>
        </c:forEach>
        <hr>
    </c:forEach>

        <%-- Button to submit quiz --%>
        <button type="submit">Submit Quiz</button>

    </form>
</div>
</body>
</html>
