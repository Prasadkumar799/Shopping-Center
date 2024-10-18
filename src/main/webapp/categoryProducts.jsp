<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ page import="com.dto.*, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Category Products</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <style>
        .product-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px; /* Space between items */
        }
        .product {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 250px;
            background-color: #f9f9f9;
        }
        .product img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
        }
        .product p {
            margin: 10px 0;
        }
        
        .button-group {
            display: flex; /* Use flexbox for layout */
            gap: 10px; /* Space between buttons */
            justify-content: center; /* Center the buttons */
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
    <div class="container">
        <div class="navbar">
            <a href="dashboard.jsp" class="btn btn-link">Home</a>
            <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
            <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
            <a href="cartitems" class="btn btn-link">Cart</a>
        </div>

        <%
            String categoryName = (String) session.getAttribute("categoryName");
        %>
        <h1 align="center"><%= categoryName %> Products</h1>
        <div class="product-grid">
            <%
            List<ProductResponse> products = (List<ProductResponse>) session.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (ProductResponse product : products) {
            %>
                <div class="product">
                    <img src="<%= product.getImageURL() %>" alt="<%= product.getProductName() %>" />
                    <p><strong><%= product.getProductName() %></strong></p>
                    <p><%= product.getProductInfo() %></p>
                    <p>$ <%= product.getProductPrice() %></p>
                    
                    <div class="button-group">
                        <form action="addcart" method="post">
                            <input type="hidden" name="productId" value="<%= product.getProductID() %>" />
                            <input type="hidden" name="quantity" value="1" />
                            <button type="submit" class="btn btn-primary">Add To Cart</button>
                        </form>
                        <form action="addfavorites" method="post">
                            <input type="hidden" name="productId" value="<%= product.getProductID() %>" />
                            <input type="hidden" name="quantity" value="1" />
                            <button type="submit" class="btn btn-secondary">Add To Favorites</button>
                        </form>
                    </div>
                </div>
            <% 
                }
            } else {
            %>
                <p>No products available for this category.</p>
            <% 
            }
            %>
        </div>
    </div>
</body>
</html>
