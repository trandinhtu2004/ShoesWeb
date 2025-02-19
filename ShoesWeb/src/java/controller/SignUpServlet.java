/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/sign-up"})
public class SignUpServlet extends HttpServlet {

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
//            out.println("<title>Servlet SignUpServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SignUpServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    //dành cho user
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        UserDAO udao = new UserDAO();
        if (!password.equals(repassword)) {
            request.setAttribute("error", "Incorrect password!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else if
                 (phone == null || phone.length() < 9 || phone.length() > 10) {
            request.setAttribute("error", "Invalid phonenumber!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        else {
            User user = udao.getUserByEmail(email);
                if (user == null) {
            UserDAO userDAO = new UserDAO();
            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPassword(password);
            u.setRoleid(0); // mặc định để là 0 cho user quyền mua hàng - order only
            u.setPhone(phone);
            userDAO.addUser(u);
            request.setAttribute("error", "Sign up successfully! Please login!");
            //login thành công
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
                    //báo lỗi nếu email tồn tại
                    request.setAttribute("error", "Email Existed!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
                }
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
        processRequest(request, response);
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
