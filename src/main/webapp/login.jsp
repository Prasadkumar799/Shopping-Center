<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<style>
  /* Center the login form */
  .login-form {
    width: 300px;
    margin: 100px auto;
    padding: 30px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }

  /* Style the inputs */
  input[type="email"], input[type="password"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  /* Style the submit button */
  button[type="submit"] {
    width: 100%;
    background-color: #4CAF50;
    color: #fff;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }

  button[type="submit"]:hover {
    background-color: #3e8e41;
  }

  /* Style for error messages */
  .error {
    color: #f00;
    font-size: 12px;
    margin-bottom: 10px;
    text-align: center;
  }
</style>
</head>
<body>
  <div class="login-form">
    <h2 align="center">Login</h2>
    
    <form action="LoginServlet" method="post">
        <label for="user_email">Email:</label>
        <input type="email" name="user_email" required>
        
        <label for="user_password">Password:</label>
        <input type="password" name="user_password" required>
        
         <label for="role">Role:</label>
        <select name="role" required>
            <option value="Buyer">Buyer</option>
            <option value="Seller">Seller</option>    
        </select>
        <br>
        <br>
        
        <button type="submit">Login</button>
        
        <!-- Display error message if login fails -->
        <div class="error">
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
                    out.println(errorMessage);
                }
            %>
        </div>
    </form>
  </div>
</body>
</html>
