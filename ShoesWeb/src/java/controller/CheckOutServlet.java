/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Cart;
import model.Order;
import model.OrdersDetails;
import model.Product;
import model.User;

/**
 *
 * @author Phuc
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/check-out"})
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
            //tao arraylist cart_list va gan session chuyen chuoi ra trang jsp
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            User auth = (User) request.getSession().getAttribute("auth"); // kiem tra user co dang nhap khong
            if (auth != null) { // user dang nhap thi dieu huong mua san pham
                if (cart_list != null) {
                    response.sendRedirect("checkout.jsp");
                } else {
                    response.sendRedirect("cart.jsp");
                }
            } else {
                request.setAttribute("error", "You need to login first!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
//        processRequest(request, response);
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(); //tao chuoi cart_list
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            User auth = (User) request.getSession().getAttribute("auth"); // dua thang user dang nhap gan session
            if (auth != null) {
                // tao doi tuong, add thong tin doi tuong, goi ham de add vao database su dung insert order
                if (cart_list.size() > 0) {
                    Order orderModel = new Order();
                    ProductDAO pdao = new ProductDAO();
                    orderModel.setTotalprice(pdao.getTotalCartPrice(cart_list));
                    orderModel.setAddress(address);
                    orderModel.setNote(note);
                    orderModel.setDate(formatter.format(date));
                    orderModel.setUser(auth);
                    OrderDAO oDao = new OrderDAO();
                    OrderDetailDAO oddao = new OrderDetailDAO();
                    boolean result = oDao.insertOrder(orderModel);
                    if (result) {
                        for (Cart c : cart_list) {
                            Product p = pdao.getSingleProduct(c.getId());
                            //lay thang order moi nhat
                            Order o1 = oDao.getLastOrder();
                            OrdersDetails od = new OrdersDetails();
                            //set order
                            od.setOrder(o1);
                            od.setProduct(p);
                            od.setPrice(c.getQuantity() * p.getPrice());
                            od.setQuantity(c.getQuantity());
                            //insert od vao detail
                            oddao.insertOrderDetail(od);
                            //sau khi mua, hang ton kho con lai se duoc tinh
                            p.setStock(p.getStock() - c.getQuantity());
                            pdao.updateProduct(p);
                        }
                    }
                    //clear cart_list
                    cart_list.clear();
                    HttpSession session = request.getSession();
                    
                    
                    session.setAttribute("message", "Ordered successfully");
                    response.sendRedirect("orders.jsp");
                    
                } else {
                    //neu check out empty thi bao loi va chuyen ve gio hang
                    HttpSession session = request.getSession();
                    session.setAttribute("message", "Your cart is empty!");
                    response.sendRedirect("cart.jsp");
                }
            } else {
                
                //chuyen ve login neu chua dang nhap
                request.setAttribute("error", "You need to login first!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
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
