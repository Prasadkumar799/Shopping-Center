<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.dto.*,com.dao.*,com.service.*, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Product Reviews</title>
    <style>
        /* Navbar Styling */
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

        /* Main Container Styling */
        .container {
            max-width: 800px;
            margin: 0 auto; /* Center the container */
            padding: 20px;
            background-color: #f8f9fa; /* Light background color */
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
        }

        /* Review Item Styling */
        .review-item {
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 20px;
        }

        .review-item h3 {
            margin-top: 0;
            font-size: 20px;
            color: #333;
        }

        .review-item p {
            margin: 5px 0;
            color: #555;
        }

        .review-item .review-date {
            font-size: 14px;
            color: #999;
        }

        .review-item .rating {
            font-weight: bold;
            color: #ffbf00; /* Gold color for rating */
        }
    </style>
</head>
<body>
    <h1 align="center">Products Reviews</h1>
    
    <!-- Navbar -->
    <div class="navbar">
        <a href="dashboard.jsp" class="btn btn-link">Home</a>
        <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
    </div>
    
    <!-- Main Container -->
    <div class="container">
        <%
        List<ReviewResponse> reviews = (List<ReviewResponse>) session.getAttribute("reviews");
           long productID = (long)session.getAttribute("productID");
       
            		if (reviews != null && !reviews.isEmpty()) {
            for (ReviewResponse review : reviews) {
        %>
        
                <!-- Review Item -->
                <div class="review-item">
                    
                    <p>Product ID: <%= review.getProductID() %></p>
                    <p>User ID: <%= review.getUser_id() %></p>
                    <p class="rating">Rating: <%= review.getRating() %>/5</p>
                    <p>Review: <%= review.getReviewText() %></p>
                    <p class="review-date">Date: <%= review.getReviewDate() %></p>
                </div>
               
        <%
            }
        } else {
        %>
            <p>No reviews available for this product.</p>
        <%
        }
        %>
         <form action="ReviewsServlet" method="post">
			 <input type="hidden" name="productID" value="<%= productID %>">
			 
			 <label for="reviewText">Comment:</label>
        <input type="text" name="reviewText" required>
        <br>
        <br>
         <label for="rating">Rating:</label>
          <input type="number"  name="rating"  value="1" min="1" max="5">
        <br>
        <br>
        <button type="submit">Add Review</button>
        	
        </form>
    </div>
</body>
</html>
