package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.RegistrationResponse;
import com.dto.SellerResponse;
import com.service.RegistrationService;
import com.service.SellerService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RegistrationService registrationService;
    private SellerService sellerSer;
    @Override
    public void init() {
        this.registrationService = new RegistrationService();
        this.sellerSer=new SellerService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");
        String role=request.getParameter("role");
        System.out.println(role);
        if(role.equals("Buyer")) {
        try {
            RegistrationResponse user = registrationService.getUserByEmailAndPassword(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user); 
   
              	response.sendRedirect("dashboard.jsp");
                
            } else {
                request.setAttribute("errorMessage", "Invalid email or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during login. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        }else {
        	try {
                SellerResponse seller=sellerSer.getSeller(email, password);
                if (seller != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("seller", seller); 
       
                  	response.sendRedirect("sellerDashboard.jsp");
                    
                } else {
                    request.setAttribute("errorMessage", "Invalid email or password.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "An error occurred during login. Please try again.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.sendRedirect("login.jsp");
    }
}
