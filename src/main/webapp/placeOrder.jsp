<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

  input[type="text"], input[type="email"], input[type="password"], input[type="tel"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
  }

  textarea {
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

  /* Add some basic styling to the error messages */
  .error {
    color: #f00;
    font-size: 12px;
    margin-bottom: 10px;
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
	<h1 align="center">Enter Address For Place Order</h1>
	 <div class="navbar">
    	<a href="dashboard.jsp" class="btn btn-link">Home</a>
        <a href="viewcategory.jsp" class="btn btn-link">Categories</a>
        <a href="FavoritesServlet" class="btn btn-link">Favorites</a>
        <a href="cartitems" class="btn btn-link">Cart</a>
    </div>
	<form action="placeOrder" method="post">
	 
  <label for="shippingAddress"> Shipping Address:</label>
  <textarea name="shippingAddress" rows="2" cols="20" ></textarea>
  <br>
  <label for="billingAddress">Billing Address:</label>
  <textarea name="shippingAddress" rows="2" cols="20"></textarea>

  <button type="submit">Place Order</button>
	</form>
</body>
</html>