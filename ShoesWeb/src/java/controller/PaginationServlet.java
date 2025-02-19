/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductPaginationServlet", urlPatterns = {"/pagination"})
public class PaginationServlet extends HttpServlet {

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
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PaginationServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet PaginationServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        // index_raw de lay index dua tren trang tag minh dang ở
        String index_raw = request.getParameter("index");
        if (index_raw == null) { //neu index rỗng thì sẽ sửa thành 1
            index_raw = "1";
        }
        int index = Integer.parseInt(index_raw); // đưa về integer
        ProductDAO pdao = new ProductDAO();
        int count = pdao.getTotalProducts(); // lấy products list trong database
        int endPage = count / 6; // tạo biến endpage, cứ mỗi lần số product hiện ra là 6 sản phẩm thì endpage tag sẽ tăng lên
        if (count % 6 != 0) {
            endPage++;
        }

        List<Product> list = pdao.pagingProducts(index);
        
        request.setAttribute("list", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.getRequestDispatcher("product.jsp").forward(request, response);
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
