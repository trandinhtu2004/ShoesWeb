/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Cart;

/**
 *
 * @author Admin
 */
@WebServlet(name = "QuantityServlet", urlPatterns = {"/quantity"})
public class QuantityServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuantityServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuantityServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //phần này giống bên trên nhưng dành cho order now
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> cart= (ArrayList<Cart>) request.getSession().getAttribute("cart");

            if (action != null && id >= 1) {
                if (action.equals("inc")) {
                    for (Cart c : cart) {
                        if (c.getId() == id) {
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            response.sendRedirect("ordernow.jsp");
                        }
                    }
                }

                if (action.equals("dec")) {
                    for (Cart c : cart) {
                        if (c.getId() == id && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            break;
                        }
                    }
                    response.sendRedirect("ordernow.jsp");
                }
            } else {
                response.sendRedirect("ordernow.jsp");
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
