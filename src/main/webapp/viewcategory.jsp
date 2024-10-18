<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.controller.*,com.dao.*,com.dto.*,com.entity.*,com.service.*,com.utils.*,java.util.ArrayList,java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categories</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <style>
        .container {
            margin-top: 20px;
        }
        .category-item {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .category-item:last-child {
            border-bottom: none;
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
        <h1 align="center">Categories</h1>
        <div class="navbar">
    	<a href="dashboard.jsp" class="btn btn-link">Home</a>
        <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
    </div>
        <div id="categories">
            <ul class="list-unstyled">
                <%
                
                CategoryService cs=new CategoryService();
        		List<CategoryResponse> categories=cs.showCategories();
                if (categories != null && !categories.isEmpty()) {
                    for (CategoryResponse category : categories) {
                %>
                    <li class="category-item">
                        <span><%= category.getCategoryName() %></span>
                        <form action="selectcategory" method="get">
                        	<input type="hidden" name="categoryID" value="<%= category.getCategoryID() %>"/>
                        	<input type="hidden" name="categoryName" value="<%= category.getCategoryName() %>"/>
                        	<button type="submit">show</button>
                        </form>
                    </li>
                <%
                    }
                } else {
                %>
                    <p>No categories available.</p>
                <%
                }
                %>
            </ul>
        </div>
    </div>
</body>
</html>
