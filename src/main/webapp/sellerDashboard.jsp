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
        <a href="createproduct.jsp" class="btn btn-link">Add Product</a>
        <a href="login.jsp" class="btn btn-link">Login</a>
        <a href="addseller.jsp" class="btn btn-link">Register</a>
        <a href="sellerdetails.jsp" class="btn btn-link">Profile</a>
        <a href="logout" class="btn btn-link">Logout</a>
    </div>

    <div class="product-grid">
        <%
        	SellerResponse seller=(SellerResponse)session.getAttribute("seller");
            if (seller != null) {
            	long sellerID=seller.getSellerID();
                ProductsService ps = new ProductsService();
                List<ProductResponse> products = ps.getProductBySellerId(sellerID);
                for (ProductResponse product : products) {
        %>
            <div class="product">
                <img src="<%= product.getImageURL() %>" alt="<%= product.getProductName() %>"/>
                <p><strong><%= product.getProductName() %></strong></p>
                <p><%= product.getProductInfo() %></p>
                <p>$ <%= product.getProductPrice() %></p>
               
                <form action="deleteProduct" method="post">
                    <input type="hidden" name="productId" value="<%= product.getProductID() %>">
                    <button type="submit" class="btn btn-secondary">Delete</button>
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
