package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.RegistrationRequest;
import com.dto.RegistrationResponse;
import com.service.RegistrationService;

@WebServlet("/users")
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RegistrationService customerService;

    @Override
    public void init() {
        this.customerService = new RegistrationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            String userIdParam = request.getParameter("user_id");

            if (userIdParam == null || userIdParam.isEmpty()) {
                getAllUsers(out);
            } else {
                getUserById(userIdParam, out);
            }
        }
    }

    private void getAllUsers(PrintWriter out) {
        try {
            List<RegistrationResponse> users = customerService.getAllUsers();
            out.println("<H1>Customer List</H1>");
            for (RegistrationResponse user : users) {
                displayUserDetails(out, user);
                out.println("<hr>");
            }
        } catch (Exception e) {
            out.println("<h1>Error retrieving user list.</h1>");
            // Consider logging the exception
        }
    }

    private void getUserById(String userIdParam, PrintWriter out) {
        try {
            Long userId = Long.parseLong(userIdParam);
            RegistrationResponse user = customerService.getUserById(userId);

            if (user != null) {
                out.println("<H1>Customer Details</H1>");
                displayUserDetails(out, user);
            } else {
                out.println("<h1>User not found.</h1>");
            }
        } catch (NumberFormatException e) {
            out.println("<h1>Invalid user ID format.</h1>");
        } catch (Exception e) {
            out.println("<h1>Error retrieving user details.</h1>");
            // Consider logging the exception
        }
    }

    private void displayUserDetails(PrintWriter out, RegistrationResponse user) {
        out.printf("<p>User ID: %d</p>", user.getUser_id());
        out.printf("<p>First Name: %s</p>", user.getUser_firstname());
        out.printf("<p>Last Name: %s</p>", user.getUser_lastname());
        out.printf("<p>Email: %s</p>", user.getUser_email());
        out.printf("<p>Contact: %s</p>", user.getUser_contact()); 
        out.printf("<p>Address: %s</p>", user.getUser_address());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try  {
            String userFirstname = request.getParameter("user_firstname");
            String userLastname = request.getParameter("user_lastname");
            String userEmail = request.getParameter("user_email");
            String userPassword = request.getParameter("user_password");
            String userContact = request.getParameter("user_contact");
            String userAddress = request.getParameter("user_address");

            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setUser_firstname(userFirstname);
            registrationRequest.setUser_lastname(userLastname);
            registrationRequest.setUser_email(userEmail);
            registrationRequest.setUser_password(userPassword);
            registrationRequest.setUser_contact(userContact);
            registrationRequest.setUser_address(userAddress);

            customerService.createUser(registrationRequest);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (NumberFormatException e) {
        	request.setAttribute("createUser", e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        } catch (Exception e) {
        	request.setAttribute("createUser", e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}

