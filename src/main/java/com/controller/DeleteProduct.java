package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.ProductsService;

@WebServlet("/deleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductsService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductsService();
        
    }
    @Override
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session=request.getSession();
        String productid= request.getParameter("productID");
        if(productid==null || productid.isEmpty()) {
        	request.setAttribute("errorMessage", "Product ID  required.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            return;
        }
        try {
        	long productID=Long.parseLong(productid);
        	boolean status=productService.deleteProduct(productID);
        	if(status) {
        		request.getRequestDispatcher("sellerDasshboard.jsp").forward(request, response);
        	}else {
        		request.setAttribute("errorMessage", "Product not Deleted");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        	}
        }catch(Exception e) {
        	request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
        
	}

}
