/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Cart;
//
/**
 *
 * @author Admin
 */

//Phúc prese
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {

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
            out.println("<title>Servlet AddToCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCartServlet at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        try (PrintWriter out = response.getWriter()) {
            //tao arraylist cart
            ArrayList<Cart> cartList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            Cart cm = new Cart(); //tao doi tuong cart dat ten cm
            cm.setId(id);
            cm.setQuantity(1);
            HttpSession session = request.getSession();
            //tao session
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list"); 
            //gan session cho array list cart
            
            if (cart_list == null) {
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                session.setAttribute("message", "Product was added to cart successfully"); // tao bien message chuyen tiep cho trang product.jsp
                response.sendRedirect("list-product"); // chuyen huong sang list product
            } else {
                cartList = cart_list;

                boolean exist = false;
                for (Cart c : cart_list) {
                    if (c.getId() == id) {
                        exist = true; // neu item da co trong cart se bao lai va dua duong dan tro lai cart.jsp
//                        out.println("<!DOCTYPE html>");
//                        out.println("<html>");
//                        out.println("<head>");
//                        out.println("<title>Servlet AddToCartServlet</title>");
//                        out.println("</head>");
//                        out.println("<body>");
//                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>Go to Cart Page</a></h3>");
//                        out.println("</body>");
//                        out.println("</html>");
                        session.setAttribute("message", "Product already in Cart"); // tao bien message chuyen tiep cho trang cart.jsp
                        response.sendRedirect("cart.jsp"); // chuyen huong sang list product
                    }
                }

                if (!exist) {
                    cartList.add(cm);
                    response.sendRedirect("list-product");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
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
