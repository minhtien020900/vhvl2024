<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<h2>JSTL Example</h2>

<%-- Biến JSTL --%>
<c:set var="message" value="Hello, JSTL!" />
<p>Message: <c:out value="${message}" /></p>

<%-- Câu lệnh điều kiện --%>
<c:if test="${5 > 3}">
  <p>5 lớn hơn 3</p>
</c:if>

<%-- Vòng lặp --%>
<c:forEach var="num" begin="1" end="5">
  <p>Number: <c:out value="${num}" /></p>
</c:forEach>
</body>
</html>