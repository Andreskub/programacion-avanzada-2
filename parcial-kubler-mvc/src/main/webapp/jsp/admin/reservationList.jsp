<%@ page import="edu.usal.domain.Reservation" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation List</title>
</head>
<body>
<h1>Reservation List</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Book ISBN</th>
        <th>Client DNI</th>
        <th>Reservation Date</th>
        <th>Approved</th>
        <th>Actions</th>
    </tr>
    <%
        List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
        if (reservations != null) {
            for (Reservation reservation : reservations) {
    %>
    <tr>
        <td><%= reservation.getId() %></td>
        <td><%= reservation.getBook().getIsbn() %></td>
        <td><%= reservation.getClient().getDni() %></td>
        <td><%= reservation.getReservationDate() %></td>
        <td><%= reservation.isApproved() ? "Yes" : "No" %></td>
        <td>
            <a href="approveReservation?id=<%= reservation.getId() %>">Approve</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
