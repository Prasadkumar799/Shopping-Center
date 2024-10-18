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
import com.service.CategoryService;
@WebServlet("/selectcategory")
public class CategoryServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        long categoryID = Long.parseLong(request.getParameter("categoryID"));
        String categoryName=request.getParameter("categoryName");
        try {
            List<ProductResponse> products = categoryService.selectCategoryById(categoryID);
            if (products != null) {
                HttpSession session = request.getSession();
                session.setAttribute("products", products); 
                session.setAttribute("categoryName", categoryName);
                response.sendRedirect("categoryProducts.jsp");
            } else {
                request.setAttribute("errorMessage", "No products found for the selected category.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error occurred while selecting category.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}
