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
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/update-user"})
public class UpdateUserServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServlet at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        UserDAO udao = new UserDAO();
        try {
            User c = udao.getUserByEmail(email);
            if (c != null) {
                request.setAttribute("user", c);
                request.getRequestDispatcher("updateuser.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Not found!");
                request.getRequestDispatcher("updateuser.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
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
    //giống product nhưng làm update user
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        String id_raw = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleid_raw = request.getParameter("roleid");
        String phone = request.getParameter("phone");
        int roleid, id;
        UserDAO udao = new UserDAO();
        try {
            roleid = Integer.parseInt(roleid_raw);
            id = Integer.parseInt(id_raw);
            if (roleid != 0 && roleid != 1) {
                request.setAttribute("error", "Incorrect role id!");
                request.getRequestDispatcher("adduser.jsp").forward(request, response);
            } else {
                User u = new User(id, name, email, password, roleid, phone);
//                u.setName(name);
//                u.setEmail(email);
//                u.setPassword(password);
//                u.setRoleid(roleid);
//                u.setPhone(phone);
                udao.updateUser(u);
                HttpSession session = request.getSession();
                session.setAttribute("message", "User was updated successfully");
                response.sendRedirect("show-users");
            }
        } catch (NumberFormatException e) {
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
