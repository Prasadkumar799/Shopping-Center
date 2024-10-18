package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.ProductResponse;
import com.dto.RegistrationResponse;
import com.service.FavoritesService;

@WebServlet("/FavoritesServlet")
public class FavoritesServlet extends HttpServlet {
    private FavoritesService favser;

    @Override
    public void init() {
        this.favser = new FavoritesService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        
        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");

        if (user != null) {
            long user_id = user.getUser_id();
            List<ProductResponse> favproducts = favser.getAllFavorites(user_id);
            if(favproducts!=null) {
            request.setAttribute("favproducts", favproducts);
            request.getRequestDispatcher("showfavorites.jsp").forward(request, response);
            
            }else {
            	request.setAttribute("favproducts", favproducts);
            	request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp"); 
        }
    }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
	        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");

	        if (user == null) {
	            response.sendRedirect("login.jsp"); 
	            return;
	        }
	        String productIdParam = request.getParameter("productId");

	        if (productIdParam == null || productIdParam.isEmpty() ) {
	            request.setAttribute("errorMessage", "Product ID and Quantity are required.");
	            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	            return;
	        }
	        try {
	        	long user_id=user.getUser_id();
	        	long productID=Long.parseLong(productIdParam);
	        	boolean status=favser.removeFromFavorite(user_id, productID);
	        	if(status) {
	        		request.setAttribute("removeFavorite", status);
		        	request.getRequestDispatcher("showfavorites.jsp").forward(request, response);
	        	}
	        }catch(Exception e) {
	        	request.setAttribute("removecart",e.getMessage());
	        	request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	        	
	        }
		
	}
}
