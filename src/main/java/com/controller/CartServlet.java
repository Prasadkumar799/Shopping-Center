package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.CartService;
import com.dto.RegistrationResponse;

@WebServlet("/addcart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartService cartService;

    @Override
    public void init() {
        this.cartService = new CartService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");
        long userId = user.getUser_id();
        

        String productIdParam = request.getParameter("productId");
        String quantityParam = request.getParameter("quantity");

        if (productIdParam == null || quantityParam == null || productIdParam.isEmpty() || quantityParam.isEmpty()) {
            request.setAttribute("errorMessage", "Product ID and Quantity are required.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            return;
        }

        try {
            long productId = Long.parseLong(productIdParam);
            int quantity = Integer.parseInt(quantityParam);
            
            System.out.println(productId+","+quantity+","+userId);
            
            boolean added = cartService.addToCart(userId, productId, quantity);
            System.out.println(added);
            if (added) {
                 request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to add item to cart.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error occurred while adding item to cart.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}
