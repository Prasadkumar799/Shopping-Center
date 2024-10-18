package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.OrderDetailsResponse;
import com.dto.RegistrationResponse;
import com.service.OrderDetailsService;


@WebServlet("/OrderDetails")
public class OrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDetailsService odser;

    @Override
    public void init() {
        this.odser = new OrderDetailsService();
    }    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }
        
        try {
        	long user_id=user.getUser_id();
        	List<OrderDetailsResponse> od=odser.getOrderdetailsByUserId(user_id);
        	if(od!=null) {
        		request.setAttribute("orderdetails", od);
        		request.getRequestDispatcher("orderDetails.jsp").forward(request, response);
        	}
        }catch(Exception e) {
        	session.setAttribute("orderDetails", e.getMessage());
    		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
	}

	
}
