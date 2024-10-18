<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.*,com.dao.*,com.service.*, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Cart</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <style>
        .cart-item {
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
        }
        .cart-item img {
            max-width: 100px;
            height: auto;
        }
        .cart-container {
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .navbar {
        margin-bottom: 20px;
        background-color: #343a40; /* Darker background color */
        padding: 15px;
        font-size: 18px; /* Increase font size */
        color: white;
        text-align: center; /* Center the text */
    }

    .navbar a {
        color: white; /* Ensure links are visible against the dark background */
        margin-right: 15px;
        text-decoration: none;
    }

    .navbar a:hover {
        text-decoration: underline;
    }
    </style>
</head>
<body>
    <h1 align="center">Your Cart</h1>
    <div class="navbar">
    	<a href="dashboard.jsp" class="btn btn-link">Home</a>
        <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
    </div>
    <div class="cart-container">
        <% 
       		List<ProductResponse> cartItems = (List<ProductResponse>)request.getAttribute("cartList");
            if (cartItems != null && !cartItems.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>Product Image</th>
                    <th>Product Name</th>
                    <th>Product Info</th>
                    <th>Product Price</th>
                    <th>Product Quantity</th>
                    <th>Move to Favorites</th>
                    <th>Remove from Cart</th>
                </tr>
                <% for (ProductResponse item : cartItems) { %>
                <tr>
                    <td><img src="<%= item.getImageURL() %>" alt="Product Image"/></td>
                    <td><%= item.getProductName() %></td>
                    <td><%= item.getProductInfo() %></td>
                    <td>$<%= item.getProductPrice() %></td>
                    <td><%= item.getProductQuantity() %></td>
                    <td>
                        <form action="addfavorites" method="post">
                            <input type="hidden" name="productId" value="<%= item.getProductID() %>">
                            <button type="submit" class="btn btn-primary">Favorite</button>
                        </form>
                    </td>
                    <td>
                        <form action="cartitems" method="post">
                            <input type="hidden" name="productId" value="<%= item.getProductID() %>">
                            <button type="submit" class="btn btn-danger">Remove</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
            <form action="placeOrder.jsp"   >     
          <button type="submit" >Place Order</button>
            </form>
            <form></form>
        <% } else { %>
        <p>Your cart is empty.</p>
        <% } %>
    </div>
</body>
</html>
