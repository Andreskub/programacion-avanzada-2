<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
</head>
<body>
<h1>Add Book</h1>
<form action="addBook" method="post">
    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn" required><br>

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br>

    <label for="pageCount">Page Count:</label>
    <input type="number" id="pageCount" name="pageCount" required><br>

    <label for="genre">Genre:</label>
    <input type="text" id="genre" name="genre" required><br>

    <label for="edition">Edition:</label>
    <input type="text" id="edition" name="edition" required><br>

    <label for="availableCopies">Available Copies:</label>
    <input type="number" id="availableCopies" name="availableCopies" required><br>

    <label for="authorId">Author ID:</label>
    <input type="number" id="authorId" name="authorId" required><br>

    <input type="submit" value="Add Book">
</form>
<a href="adminMenu">Back to Admin Menu</a>
</body>
</html>
