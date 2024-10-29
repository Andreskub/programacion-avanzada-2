<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client Menu</title>
</head>
<body>
<h1>Client Menu</h1>
<h2>Welcome, <%= request.getSession().getAttribute("user") %></h2>
<ul>
    <li><a href="searchBookServlet">Search Book</a></li>
    <li><a href="reserveBookServlet">Reserve Book</a></li>
    <li><a href="logoutServlet">Logout</a></li>
</ul>
</body>
</html>
