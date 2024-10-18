<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.*, com.service.ProductsService, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <style>
    .product-grid {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
    }

    .product {
        flex: 1 1 calc(23.333% - 20px); /* Adjust for 3 items per row */
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
        margin-bottom: 20px;
        text-align: center;
        border-radius: 8px;
        background-color: #fff;
    }

    .product img {
        max-width: 100%;
        height: auto;
        margin-bottom: 10px;
        border-radius: 8px;
    }

    .product p {
        margin: 5px 0;
    }

    .product button {
        margin-top: 10px;
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
<body >
    <h1 align="center">Shopping Center</h1>
    <div class="navbar">
        <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
        <a href="orderDetails.jsp" class="btn btn-link" >Order Details</a>
        <a href="customerbyid.jsp" class="btn btn-link">Profile</a>
        <a href="logout" class="btn btn-link">Logout</a>
    </div>

    <div class="product-grid">
        <%
            RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");
            if (user != null) {
                ProductsService ps = new ProductsService();
                List<ProductResponse> products = ps.getAllProducts();
                int quantity = 1;
                for (ProductResponse product : products) {
        %>
            <div class="product">
                <img src="<%= product.getImageURL() %>" alt="<%= product.getProductName() %>"/>
                <p><strong><%= product.getProductName() %></strong></p>
                <p><%= product.getProductInfo() %></p>
                <p>$ <%= product.getProductPrice() %></p>
                <form action="addcart" method="post">
                    <input type="hidden" name="productId" value="<%= product.getProductID() %>">
                    <input type="number"  name="quantity" class="quantity-input" value="1" min="1" max="<%= product.getProductQuantity() %>">
                    <button type="submit" class="btn btn-primary">Add To Cart</button>
                </form>
                <form action="addfavorites" method="post">
                    <input type="hidden" name="productId" value="<%= product.getProductID() %>">
                    <input type="hidden" name="quantity" value="1">
                    <button type="submit" class="btn btn-secondary">Add To Favorites</button>
                </form>
                <form action="ReviewsServlet" method="get">
   					 <input type="hidden" name="productID" value="<%= product.getProductID() %>">
    				 <button type="submit" class="btn btn-secondary">Reviews</button>
				</form>

            </div>
        <%
                }
            } else {
        %>
            <p>Please <a href="login.jsp">login</a> to view products.</p>
        <%
            }
        %>
    </div>
</body>
</html>
