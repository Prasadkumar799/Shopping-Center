<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <style>
        .error-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #dc3545;
            border-radius: 5px;
            background-color: #f8d7da;
            color: #721c24;
        }
        .error-container h1 {
            font-size: 24px;
        }
        .error-container p {
            font-size: 18px;
        }
        .btn {
            margin-top: 20px;
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
<div class="navbar">
    	<a href="dashboard.jsp" class="btn btn-link">Home</a>
        <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
    </div>
    <div class="error-container">
        <h1>Error</h1>
        <p><%= request.getAttribute("errorMessage") %></p>
        <a href="index.jsp" class="btn btn-primary">Return to Home</a>
    </div>
</body>
</html>
