<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reviews Form</title>
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

  input[type="number"], select {
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
<h1 align="center"> Add Reviews</h1>
<form action="reviews" method="post">
  
   

  <label for="rating">Product Rating:</label>
  <input type="number" name="rating" required>
  <br>

  <label for="reviewtext">Product Info:</label>
  <textarea name="reviewtext" rows="5" cols="30"></textarea>
  <br>
  <br>

  <button type="submit">Submit</button>

</form>

</body>
</html>
