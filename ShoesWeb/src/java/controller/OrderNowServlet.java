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
 * @author Admin
 */
@WebServlet(name = "OrderNowServlet", urlPatterns = {"/order-now"})
public class OrderNowServlet extends HttpServlet {

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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet OrderNowServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet OrderNowServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

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
            PrintWriter out = response.getWriter();
        try{
            User auth = (User) request.getSession().getAttribute("auth");
            HttpSession session = request.getSession();
            /*lay id-sanpham cua product tu database*/
            if (auth != null) {
                String productId = request.getParameter("id");
                
                
                ProductDAO pdao = new ProductDAO();
                
                
                Product p = pdao.getSingleProduct(Integer.parseInt(productId));
                
                
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                    //quantity = 0 thi se cho = 1
                    productQuantity = 1;
                }
                ArrayList<Cart> cart = new ArrayList<>();
                cart.add(new Cart(p.getId(), p.getName(), p.getDescription(), p.getCategory(), p.getPrice(), p.getStock(), p.getImage(), productQuantity));
                //gan session cho chuoi cart va chuyen ve ordernow.jsp
                session.setAttribute("cart", cart);
                response.sendRedirect("ordernow.jsp");
            } else {
                //neu chua dang nhap thi ve login.jsp
                response.sendRedirect("login.jsp");
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
        //lay du lieu nhap tu ban phim
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //format date
            Date date = new Date();
            ArrayList<Cart> cart = (ArrayList<Cart>) request.getSession().getAttribute("cart"); //tao chuoi cart va gan session
            User auth = (User) request.getSession().getAttribute("auth");
            if (auth != null) { // xac nhan xem co tai khoan hay khong, neu khong thi chuyen huong sang login
                if (cart != null) { //cart co mat hang thi dua thong tin vao order 
                    Order orderModel = new Order();
                    ProductDAO pdao = new ProductDAO();
                    orderModel.setTotalprice(pdao.getTotalCartPrice(cart));
                    orderModel.setAddress(address);
                    orderModel.setNote(note);
                    orderModel.setDate(formatter.format(date));
                    orderModel.setUser(auth);
                    OrderDAO oDao = new OrderDAO();
                    OrderDetailDAO oddao = new OrderDetailDAO();
                    boolean result = oDao.insertOrder(orderModel);
                    if (result) { //no ton tai và result trả về true
                        for (Cart c : cart) { // mỗi mặt hàng sẽ có thông tin và được insert cho orderdetail
                            Product p = pdao.getSingleProduct(c.getId());
                            Order o1 = oDao.getLastOrder();
                            OrdersDetails od = new OrdersDetails();
                            od.setOrder(o1);
                            od.setProduct(p);
                            od.setPrice(c.getQuantity() * p.getPrice());
                            od.setQuantity(c.getQuantity());
                            oddao.insertOrderDetail(od); // insert
                            p.setStock(p.getStock() - c.getQuantity()); // trừ mặt hàng đã bán và update số lượng còn lại
                            pdao.updateProduct(p);
                        }
                    }
                    cart.clear(); // xóa mặt hàng trong chuỗi và chuyển hướng về cart.jsp
                    request.getSession().removeAttribute("cart");
                    HttpSession session = request.getSession();
                    session.setAttribute("message", "Ordered successfully");
                    response.sendRedirect("orders.jsp");
                } else {
                    response.sendRedirect("cart.jsp");
                }
            } else {
                response.sendRedirect("login.jsp");
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
