<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reserve a Book</title>
</head>
<body>
<h1>Reserve a Book</h1>
<form action="<%= request.getContextPath() %>/reserveBook" method="post">
    <label for="isbn">Enter ISBN:</label>
    <input type="text" id="isbn" name="isbn" required>
    <input type="submit" value="Reserve">
</form>

<p style="color:green;">
    <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
</p>
</body>
</html>
