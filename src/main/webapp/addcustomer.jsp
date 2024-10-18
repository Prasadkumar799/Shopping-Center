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
</style>
</head>
<body>
<h1 align="center">Add User Details To Register</h1>
<form action="login.jsp" method="post">

  <label for="user_firstname">First Name:</label>
  <input type="text" name="user_firstname" required>
  <br>

  <label for="user_lastname">Last Name:</label>
  <input type="text" name="user_lastname" required>
  <br>

  <label for="user_email">Email:</label>
  <input type="email" name="user_email" required>
  <br>

  <label for="user_password">Password:</label>
  <input type="password" name="user_password" required>
  <br>

  <label for="user_contact">Contact Number:</label>
  <input type="tel" name="user_contact" required>
  <br>

  <label for="user_address">Address:</label>
  <textarea name="user_address" rows="3" cols="20"></textarea>
  <br>

  <button type="submit">Register</button>

</form>

</body>
</html>
