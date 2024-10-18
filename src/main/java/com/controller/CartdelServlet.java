package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.ProductResponse;
import com.dto.RegistrationResponse;
import com.service.CartService;


@WebServlet("/cartitems")
public class CartdelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private CartService cartService;

	    @Override
	    public void init() {
	        this.cartService = new CartService();
	    }
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");

	        if (user == null) {
	            response.sendRedirect("login.jsp"); 
	            return;
	        }
	        
	        try {
	        	Long user_id=user.getUser_id();
	        	List<ProductResponse> cartList=cartService.cartItems(user_id);
	        	if(cartList!=null) {
	        	request.setAttribute("cartList", cartList);
	        	request.getRequestDispatcher("viewCart.jsp").forward(request, response);
	        	}else {
	        		String str="No Products in Cart";
	        		request.setAttribute("cartItems", str);
	        		request.getRequestDispatcher("errorPage.jsp").forward(request,response);
	        	}
	        }catch(Exception e) {
	        	request.setAttribute("cartlist",e.getMessage());
	        	request.getRequestDispatcher("errorPage.jsp").forward(request, response);
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
	        	System.out.println(user_id+" "+productID);
	        	boolean status=cartService.deleteFromCart(user_id, productID);
	        	if(status) {
	        		request.setAttribute("removecart", status);
		        	request.getRequestDispatcher("viewCart.jsp").forward(request, response);
	        	}
	        }catch(Exception e) {
	        	request.setAttribute("removecart",e.getMessage());
	        	request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	        	
	        }
		
	}

}
