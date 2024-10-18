<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Shopping Center</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        color: #333;
        margin: 0;
        padding: 0;
        text-align: center;
    }
    h1 {
        color: #4CAF50;
        margin-top: 50px;
    }
    p {
        font-size: 18px;
        margin: 20px 0;
    }
    a {
        display: inline-block;
        margin: 10px;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        font-weight: bold;
    }
    a:hover {
        background-color: #45a049;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    ul li {
        font-size: 18px;
        margin: 10px 0;
        color: #555;
    }
    hr {
        margin: 30px 0;
        border: none;
        border-top: 1px solid #ddd;
    }
    .container {
        max-width: 800px;
        margin: 0 auto;
        background-color: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .call-to-action {
        margin-top: 30px;
        font-size: 20px;
    }
</style>
</head>
<body align="center">
	<h1>Welcome to the Shopping Center</h1>
	<p>Your one-stop destination for all your shopping needs. Discover a wide range of products across various categories.</p>
	<div>
		<a href="login.jsp">Login</a>  
		<a href="addcustomer.jsp">User Register</a> 
		<a href="addseller.jsp">Seller Register</a>
	</div>
	<hr>
	<h2>Why Shop With Us?</h2>
	<ul style="list-style-type: none;">
		<li> Wide variety of products</li>
		<li> Easy and secure checkout</li>
		<li> Fast delivery</li>
		<li> 24/7 customer support</li>
	</ul>
	<p><strong>Note:</strong> To access our exclusive deals and offers, you must register for an account. If you already have an account, please log in.</p>
	<div>
		<h3>Start Shopping Now!</h3>
		<p>Don't miss out on our latest products and deals. Browse our categories and start adding items to your cart!</p>
	</div>
</body>
</html>
