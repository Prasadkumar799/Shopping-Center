<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Order</title>
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

        input[type="number"], input[type="text"], select {
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
    </style>
</head>
<body>
    <h1 align="center">Create Order</h1>
    <form action="orders" method="post">
        <input type="hidden" name="action" value="create">
        <label for="user_id">User ID:</label>
        <input type="number" name="user_id" required>
        
        <label for="productID">Product ID:</label>
        <input type="number" name="productID" required>
        
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" required>

        <label for="unitPrice">Unit Price:</label>
        <input type="number" step="0.01" name="unitPrice" required>

        <label for="status">Status:</label>
        <select name="status" required>
            <option value="Pending">Pending</option>
            <option value="Shipped">Shipped</option>
            <option value="Delivered">Delivered</option>
            <option value="Cancelled">Cancelled</option>
        </select>

        <button type="submit">Create Order</button>
    </form>
</body>
</html>
