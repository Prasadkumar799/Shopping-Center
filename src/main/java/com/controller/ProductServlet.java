package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.ProductRequest;
import com.dto.ProductResponse;
import com.service.ProductsService;




@WebServlet("/products/*")
public class ProductServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private ProductsService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductsService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String path = request.getPathInfo();
        String categoryIdParam = request.getParameter("categoryID");
        
        if (path != null && path.equals("/all")) {
            getAllProducts(out);
        } else if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            getProductsByCategoryId(categoryIdParam, out);
        } else {

        	String productIdParam = request.getParameter("productID");
            if (productIdParam != null && !productIdParam.isEmpty()) {
                getProductById(productIdParam, out);
            } else {
                out.println("<h1>Error: Product ID or Category ID is required.</h1>");
            }
        }
    }

    private void getAllProducts(PrintWriter out) {
        try {
            List<ProductResponse> products = productService.getAllProducts();
            out.println("<H1>Product List</H1>");
            for (ProductResponse p : products) {
                out.printf("<p>Product ID: %d</p>", p.getProductID());
                out.printf("<p>Product Name: %s</p>", p.getProductName());
                out.printf("<p>Product Info: %s</p>", p.getProductInfo());
                out.printf("<p>Product Price: %.2f</p>", p.getProductPrice());
                out.printf("<p>Product Quantity: %d</p>", p.getProductQuantity());
                out.printf("<p>Seller ID: %d</p>", p.getSellerID());
                out.printf("<p>Image URL: %s</p>", p.getImageURL());
                out.println("<hr>"); // Adds a horizontal line between products
            }
        } catch (Exception e) {
            out.println("<h1>Error retrieving product list.</h1>");
            e.printStackTrace();
        }
    }

    private void getProductById(String productIdParam, PrintWriter out) {
        try {
            Long productId = Long.parseLong(productIdParam); // Convert product ID from string to long
            ProductResponse product = productService.getProductById(productId);

            if (product != null) {
                out.println("<H1>Product Details</H1>");
                out.printf("<p>Product ID: %d</p>", product.getProductID());
                out.printf("<p>Product Name: %s</p>", product.getProductName());
                out.printf("<p>Product Info: %s</p>", product.getProductInfo());
                out.printf("<p>Product Price: %.2f</p>", product.getProductPrice());
                out.printf("<p>Product Quantity: %d</p>", product.getProductQuantity());
                out.printf("<p>Seller ID: %d</p>", product.getSellerID());
                out.printf("<p>Image URL: %s</p>", product.getImageURL());
            } else {
                out.println("<h1>Product not found.</h1>");
            }
        } catch (NumberFormatException e) {
            out.println("<h1>Invalid product ID format.</h1>");
        } catch (Exception e) {
            out.println("<h1>Error retrieving product details.</h1>");
            e.printStackTrace();
        }
    }

    private void getProductsByCategoryId(String categoryIdParam, PrintWriter out) {
        try {
            Long categoryId = Long.parseLong(categoryIdParam); // Convert category ID from string to long
            List<ProductResponse> products = productService.getProductsByCategoryId(categoryId);

            if (products != null && !products.isEmpty()) {
                out.println("<H1>Products for Category ID: " + categoryId + "</H1>");
                for (ProductResponse p : products) {
                    out.printf("<p>Product ID: %d</p>", p.getProductID());
                    out.printf("<p>Product Name: %s</p>", p.getProductName());
                    out.printf("<p>Product Info: %s</p>", p.getProductInfo());
                    out.printf("<p>Product Price: %.2f</p>", p.getProductPrice());
                    out.printf("<p>Product Quantity: %d</p>", p.getProductQuantity());
                    out.printf("<p>Seller ID: %d</p>", p.getSellerID());
                    out.printf("<img src='%s' style='height: 150px; max-width: 180px'>Image URL: %s</p>", p.getImageURL());
                    out.println("<hr>"); // Adds a horizontal line between products
                }
            } else {
                out.println("<h1>No products found for Category ID: " + categoryId + "</h1>");
            }
        } catch (NumberFormatException e) {
            out.println("<h1>Invalid category ID format.</h1>");
        } catch (Exception e) {
            out.println("<h1>Error retrieving products by category.</h1>");
            e.printStackTrace();
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        try {
            String productName = request.getParameter("productName");
            String productInfo = request.getParameter("productInfo");
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));
            int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            int sellerID = Integer.parseInt(request.getParameter("sellerID"));
            String imageURL = request.getParameter("imageURL");

            ProductRequest product = new ProductRequest();
            product.setProductName(productName);
            product.setProductInfo(productInfo);
            product.setProductPrice(productPrice);
            product.setProductQuantity(productQuantity);
            product.setCategoryID(categoryID);
            product.setSellerID(sellerID);
            product.setImageURL(imageURL);

            productService.createProduct(product);
            request.getRequestDispatcher("sellerDashboard.jsp").forward(request, response);
        }  catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);            
        }
        
    }
}
