<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search Books</title>
</head>
<body>
<h1>Search Books</h1>
<form action="searchBooks" method="post">
    <input type="text" name="query" placeholder="Title or ISBN">
    <button type="submit">Search</button>
</form>

<c:if test="${not empty books}">
    <h2>Search Results:</h2>
    <ul>
        <c:forEach var="book" items="${books}">
            <li>${book.title} - ${book.isbn}</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
