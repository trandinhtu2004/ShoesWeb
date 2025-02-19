/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
//Tu
import dal.UserDAO;
import java.io.IOException;
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
@WebServlet(name = "AddUserServlet", urlPatterns = {"/add-user"})
public class AddUserServlet extends HttpServlet {

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

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String roleid_raw = request.getParameter("roleid");
        String phone = request.getParameter("phone");
        //lay du lieu nhap vao
        int roleid;
        UserDAO udao = new UserDAO();
        try {
            roleid = Integer.parseInt(roleid_raw);
            if (roleid != 0 && roleid != 1) { // neu role id khac 0 hoac 1 bao loi
                request.setAttribute("error", "Incorrect role id!");
                //dieu huong ve 
                request.getRequestDispatcher("adduser.jsp").forward(request, response);
            } else if
                 (phone == null || phone.length() < 9 || phone.length() > 10) {
                //neu so dien thoai rong < hoac > 9 - 10 thi bao loi
                request.setAttribute("error", "Invalid phonenumber!!!");
                //dieu huong
                request.getRequestDispatcher("adduser.jsp").forward(request, response);
            }else {
                User user = udao.getUserByEmail(email); // lay user tu email trong database
                if (user == null) {
                    User u = new User();
                    u.setName(name);
                    u.setEmail(email);
                    u.setPassword(password);
                    u.setRoleid(roleid);
                    u.setPhone(phone);
                    udao.addUserAdmin(u);
                    HttpSession session = request.getSession();
                    session.setAttribute("message", "User was added successfully");
                    response.sendRedirect("show-users");
                } else {
                    //neu email ton tai thi bao loi
                    request.setAttribute("error", "Email existed!");
                    request.getRequestDispatcher("adduser.jsp").forward(request, response);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
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
