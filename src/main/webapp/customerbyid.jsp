<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dto.*,com.dao.*,com.service.*, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Retrieve User by ID</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <style>
        /* Add some basic styling to the form */
        form {
            width: 50%;
            margin: 40px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Style the labels and inputs */
        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
        }

        /* Style the submit button */
        button[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #3e8e41;
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
	<h1 align="center">User Details</h1>
	<div class="container">
        <div class="navbar">
    	<a href="dashboard.jsp" class="btn btn-link">Home</a>
        <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
        </div>
    <div>
    	<%
    	RegistrationResponse user=(RegistrationResponse)session.getAttribute("user");
    	%>
    	<form action="">
    	<table>
    		<tr>
    			<td>User ID : </td>
    			<td><%= user.getUser_id() %></td>
    		</tr>
    		<tr>
    			<td>User First Name : </td>
    			<td><%= user.getUser_firstname() %></td>
    		</tr>
    		<tr>
    			<td>User Last Name : </td>
    			<td><%= user.getUser_lastname() %></td>
    		</tr>
    		<tr>
    			<td>User Email : </td>
    			<td><%= user.getUser_email() %></td>
    		</tr>
    		<tr>
    			<td>User Contact : </td>
    			<td><%= user.getUser_contact() %></td>
    		</tr>
    		<tr>
    			<td>User Address : </td>
    			<td><%= user.getUser_address() %></td>
    		</tr>
    		<tr>
    			<td>User Password : </td>
    			<td><%= user.getUser_password() %></td>
    		</tr>
    		
    	</table>
    	
    	</form>
    </div>
</body>
</html>
