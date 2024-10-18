<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Form</title>
<style>
  /* Add some basic styling to the form */
  form {
    width: 30%;
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

  input[type="text"], input[type="number"], textarea {
    width: 90%;
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
  label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }

    input[type="text"],
    input[type="number"],
    textarea,
    select {
      width: 95%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #666;
      border-radius: 5px;
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
<h1 align="center">Add Product Details</h1>
<div class="navbar">
        <a href="sellerDashboard.jsp" class="btn btn-link">Home</a>
        <a href="login.jsp" class="btn btn-link">Login</a>
        <a href="logout" class="btn btn-link">Logout</a>
        <a href="createproduct.jsp" class="btn btn-link">Add Product</a>
    </div>
<form action="products" method="post">

  <label for="productName">Product Name:</label>
  <input type="text" name="productName" required>
  <br>

  <label for="productInfo">Product Info:</label>
  <textarea name="productInfo" rows="5" cols="30"></textarea>
  <br>

  <label for="productPrice">Product Price:</label>
  <input type="number" name="productPrice" required>
  <br>

  <label for="productQuantity">Product Quantity:</label>
  <input type="number" name="productQuantity" required>
  <br>

  <label for="categoryID">Category :</label>
  <select name="categoryID" required>
      <option value="1">Electronic</option>
      <option value="2">Fashion</option>
      <option value="3">Home Decor</option>
  </select>
  <br>
  

  <label for="sellerID">Seller ID:</label>
  <input type="number" name="sellerID" required>
  <br>

  <label for="imageURL">Image URL:</label>
  <input type="text" name="imageURL">
  <br>

  <button type="submit">Add</button>

</form>

</body>
</html>
