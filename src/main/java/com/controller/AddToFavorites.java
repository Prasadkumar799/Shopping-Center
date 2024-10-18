package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.RegistrationResponse;
import com.service.FavoritesService;


@WebServlet("/addfavorites")
public class AddToFavorites extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavoritesService favser;

    @Override
    public void init() {
        this.favser = new FavoritesService();
    }  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        HttpSession session = request.getSession();
        
        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");
        String productId = request.getParameter("productId");
        if(user==null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);

        	
        }
        System.out.println(user);
        System.out.println(productId);
        if (productId == null  || productId.isEmpty() ) {
            request.setAttribute("errorMessage", "Product ID is required.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            return;
        }
        else {
        	try {
        		long productID=Long.parseLong(productId);
        		long user_id=user.getUser_id();
        		
        		boolean addFavorite=favser.addToFavorites(user_id, productID);
        		
        		if(addFavorite) {
        			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        		}else {
        			session.setAttribute("addFavorite", "Error while adding to favorites");
            		request.getRequestDispatcher("errorPage.jsp").forward(request, response);	
        		}
        	}catch(Exception e) {
        		session.setAttribute("addFavorite", e.getMessage());
        		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        	}
        }
	}

}
