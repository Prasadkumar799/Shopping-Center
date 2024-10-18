<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration Form</title>
<link href=”bootstrap/css/bootstrap.min.css” rel=”stylesheet” type=”text/css” />
<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>
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

  input[type="text"], input[type="email"], input[type="password"] {
    width: 95%;
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
<h1 align="center">Add Details To Register</h1>

<body >
    <h1 align="center">Shopping Center</h1>
    <div class="navbar">
   		<a href="sellerDashboard.jsp" class="btn btn-link">Home</a>
        <a href="login.jsp" class="btn btn-link">Login</a>
        <a href="logout" class="btn btn-link">Logout</a>
    </div>
<form action="SellerServlet" method="post">

  <label for="sellerName">Name:</label>
  <input type="text" name="sellerName" required>
  <br>

  <label for="sellerEmail">Email:</label>
  <input type="text" name="sellerEmail" required>
  <br>

 

  <label for="sellerPassword">Password:</label>
  <input type="password" name="sellerPassword" required>
  <br>

  <button type="submit">Register</button>
  

</form>

</body>
</html>
