<%--
  Created by IntelliJ IDEA.
  User: JEKA
  Date: 10.05.2023
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quest</title>
</head>
<body>
<section>
    <a href="index.html">Start again</a>
    <h3>${question.content}</h3>

    <form action="${question.type == "LOST" || question.type == "WON" ? 'auth' : 'quest'}" method="get">
        <c:forEach items="${answers}" var="answer">
            <jsp:useBean id="answer" type="com.web.application.gamequest.model.Answer"/>
            <input type="radio" name="answerid" value="${answer.id}">${answer.content}<br>
        </c:forEach>
        <button type="submit">${question.type == "LOST" || question.type == "WON" ? 'Play again' : 'Answer'}</button>
    </form>
</section>
<br>
<br>
<br>
<br>
<br>
<p>Statistics:<br/>
    IP address: <i>${ip}</i><br/>
    Name in game: <i>${userName}</i><br/>
    Number of games: <i>${attempt}</i><br/>
</p>
</body>
</html>