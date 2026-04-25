<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Danh sách phần tử</title>
</head>
<body>
<h1>Danh sách 20 phần tử</h1>
<h3>Danh sách bằng Scriptlet</h3>
<ul>
  <%
    // Tạo danh sách 20 phần tử
    for (int i = 1; i <= 10; i++) {
      String item = "Phần tử " + i;
  %>
  <li><%= item %></li>
  <%
    }
  %>
</ul>

<h3>Danh sách bằng JSTL</h3>
<ul>
  <c:forEach var="i" begin="11" end="20">
    <li>Phần tử ${i}</li>
  </c:forEach>
</ul>
</body>
</html>