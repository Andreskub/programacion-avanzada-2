<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Approve Reservation</title>
</head>
<body>
<h1>Approve Reservation</h1>
<form action="approveReservation" method="post">
    <input type="hidden" name="id" value="${reservation.id}"/>
    <p>Book: ${reservation.book.title}</p>
    <p>Client: ${reservation.client.dni}</p>
    <p>Reservation Date: ${reservation.reservationDate}</p>
    <p>Return Due Date: ${reservation.returnDueDate}</p>
    <p>Approved: ${reservation.approved ? 'Yes' : 'No'}</p>
    <button type="submit">Approve</button>
    <a href="reservationList">Cancel</a>
</form>
</body>
</html>
