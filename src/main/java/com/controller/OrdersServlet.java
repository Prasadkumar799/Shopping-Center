package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.OrdersRequest;
import com.dto.RegistrationResponse;
import com.service.CartService;
import com.service.OrdersService;

@WebServlet("/placeOrder")
public class OrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrdersService ordersService;
    private CartService cartSer;

    @Override
    public void init() {
        this.ordersService = new OrdersService();
        this.cartSer = new CartService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        
        RegistrationResponse user = (RegistrationResponse) session.getAttribute("user");

        if (user == null) {
            session.setAttribute("confirmOrder", "User not logged in");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            long userId = user.getUser_id();
            String status = "Placed";
            String shipping = request.getParameter("shippingAddress");
            String billing = request.getParameter("billingAddress");
            OrdersRequest order = new OrdersRequest(userId, status, shipping, billing);
            boolean check = ordersService.createOrder(order);

            if (check) {
                session.setAttribute("confirmOrder", "Successful");
                request.getRequestDispatcher("successOrder.jsp").forward(request, response);
            } else {
                session.setAttribute("confirmOrder", "Unsuccessful");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("confirmOrder", "Unsuccessful");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ordersService = null;
        cartSer = null;
    }
}
