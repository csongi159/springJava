<%@ page import="edu.bbte.idde.model.edu.vcim2315.idde.vcim2315.Component" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Components List</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Components List</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Type</th>
        <th>Price</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Component> components = (List<Component>) request.getAttribute("components");
        for (Component component : components) {
    %>
    <tr>
        <td><%= component.getId() %></td>
        <td><%= component.getName() %></td>
        <td><%= component.getType() %></td>
        <td><%= component.getPrice() %></td>
        <td><%= component.getQuantity() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<form method="post" action="logout">
    <button type="submit">Logout</button>
</form>
</body>
</html>