package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.SellerRequest;
import com.service.SellerService;


@WebServlet("/SellerServlet")
public class SellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private SellerService sellerser;  
	 @Override
	    public void init() {
	        this.sellerser = new SellerService();
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
        	String name=request.getParameter("sellerName");
        	String email=request.getParameter("sellerEmail");
        	String password=request.getParameter("sellerPassword");
        	
        	SellerRequest seller=new SellerRequest(name,email,password);
        	boolean status=sellerser.addSeller(seller);
        	if(status) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
        	}
        }catch (Exception e) {
        	request.setAttribute("createUser", e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
	}

}
