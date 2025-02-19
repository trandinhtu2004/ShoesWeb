/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.AWTError;
import model.User;

/**
 *
 * @author Tu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/user-login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
//dieu huong ve login.jsp
        response.sendRedirect("login.jsp");
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
        try{
            PrintWriter out = response.getWriter();
            //lay du lieu nhap vao 
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");
            //lay database
            UserDAO udao = new UserDAO();
            User user = udao.userLogin(email, password);
            //user khac null ->
            if (user != null ) {
//              neu roleid = 0 thi la user con 1 thi la admin
//              tuong tac giua chu va khach hang
                if (user.getRoleid() == 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("auth", user);
                    response.sendRedirect("index.jsp");
                }
                else if (user.getRoleid() == 1) {
                    HttpSession session = request.getSession();
                    session.setAttribute("auth", user);
                    response.sendRedirect("to-admin");
                }
            }
        } catch (Exception e) {
            //email hoac pas sai thi bao loi
            System.out.println(e);
              request.setAttribute("error", "Username or Password is invalid!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
