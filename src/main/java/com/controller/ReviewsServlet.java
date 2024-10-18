package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.RegistrationResponse;
import com.dto.ReviewRequest;
import com.dto.ReviewResponse;
import com.service.ReviewsService;

/**
 * Servlet implementation class ReviewsServlet
 */
@WebServlet("/ReviewsServlet")
public class ReviewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReviewsService reviewSer;

    @Override
    public void init() {
        this.reviewSer = new ReviewsService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        
        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }
        
        String productIDStr = request.getParameter("productID");
        if (productIDStr == null || productIDStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is missing or invalid.");
            return;
        }

        
        try {
        	Long  productID = Long.parseLong(productIDStr);
        	 long userId = user.getUser_id();
             List<ReviewResponse> reviews = reviewSer.showReviews(productID);
             session.setAttribute("productID", productID);
             session.setAttribute("reviews", reviews);
             request.getRequestDispatcher("productreviews.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID must be a valid number.");
            return;
        }
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");
        long userId = user.getUser_id();
        
        long productID=(long) session.getAttribute("productID");
//        String productId = request.getParameter("productId");
        String ratings=request.getParameter("rating");
        String comments=request.getParameter("reviewText");
        
        System.out.println(productID+","+ratings+","+comments);
        if ( ratings==null ||comments==null || ratings.isEmpty()|| comments.isEmpty() ) {
            request.setAttribute("errorMessage", "Product ID , rating , and comment are required.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            return;
        }

        try {
            int rating = Integer.parseInt(ratings);
            
            System.out.println(productID+","+userId);
            ReviewRequest review=new ReviewRequest(productID,userId,rating,comments);
            boolean added = reviewSer.addReview(review);
            System.out.println(added);
            if (added) {
//            	response.sendRedirect("ReviewsServlet?productID=1"+productID);
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
