<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.ProductResponse, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Favorite Products</title>
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

<body>
    <h1 align="center">Favorite Products</h1>
    <div class="navbar">
    	<a href="dashboard.jsp" class="btn btn-link">Home</a>
        <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
    </div>
    <%
        List<ProductResponse> favproducts = (List<ProductResponse>) request.getAttribute("favproducts");
        if (favproducts != null && !favproducts.isEmpty()) {
     for (ProductResponse favpro : favproducts) { %>
       <div class="product">
                <img src="<%= favpro.getImageURL() %>" alt="<%= favpro.getProductName() %>"/>
                <p><strong><%= favpro.getProductName() %></strong></p>
                <p><%= favpro.getProductInfo() %></p>
                <p>$ <%= favpro.getProductPrice() %></p>
                <form action="addcart" method="post">
                    <input type="hidden" name="productId" value="<%= favpro.getProductID() %>">
                    <input type="hidden" name="quantity" value=1>
                    <button type="submit" class="btn btn-primary">Add To Cart</button>
                </form>
                <form action="FavoritesServlet" method="post">
                    <input type="hidden" name="productId" value="<%= favpro.getProductID() %>">
                    <button type="submit" class="btn btn-secondary">Remove</button>
                </form>
            </div>
        <% } %>
    <% } else { %>
    <p>No favorite products found.</p>
    <% } %>
</body>
</html>
