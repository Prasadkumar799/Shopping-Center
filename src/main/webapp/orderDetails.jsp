<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.dto.*, com.dao.*, com.service.*, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Order Details</title>
    <style>
        .navbar {
            margin-bottom: 20px;
            background-color: #343a40;
            padding: 15px;
            font-size: 18px;
            color: white;
            text-align: center;
        }

        .navbar a {
            color: white;
            margin-right: 15px;
            text-decoration: none;
        }

        .navbar a:hover {
            text-decoration: underline;
        }

        table {
            margin: auto;
            border-collapse: collapse;
            width: 80%;
        }

        th, td {
            border: 1px solid #000;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1 align="center">Order Details</h1>

    <div class="navbar">
    	<a href="dashboard.jsp" class="btn btn-link">Home</a>        
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
        <a href="logout" class="btn btn-link">Logout</a>
    </div>

    <%
        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");
        if (user != null) {
            OrderDetailsService ods = new OrderDetailsService();
            List<OrderDetailsResponse> details = ods.getOrderdetailsByUserId(user.getUser_id());
    %>

    <table>
        <tr>
            <th>Order Detail ID</th>
            <th>Order ID</th>
            <th>User ID</th>
            <th>Product ID</th>
            <th>Total Amount</th>
            <th>Status</th>
        </tr>
        <%
            for (OrderDetailsResponse item : details) {
        %>
        <tr>
            <td><%= item.getOrderDetailID() %></td>
            <td><%= item.getOrderID() %></td>
            <td><%= item.getUser_id() %></td>
            <td><%= item.getProductID() %></td>
            <td><%= item.getUnitPrice() %></td>
            <td>Successful</td>
        </tr>
        <%
            }
        %>
    </table>

    <%
        } else {
    %>
        <p align="center">No user found. Please <a href="login.jsp">login</a>.</p>
    <%
        }
    %>
</body>
</html>
