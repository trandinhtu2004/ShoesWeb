/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
//TU
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;

/**
 *
 * @author Admin
 */
//admin
@WebServlet(name = "AddProductServlet", urlPatterns = {"/add-product"})
public class AddProductServlet extends HttpServlet {

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

        //lay du lieu tu web
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String price_raw = request.getParameter("price");
        String stock_raw = request.getParameter("stock");
        String image = request.getParameter("image");
        double price;
        int stock;
        //
        ProductDAO pdao = new ProductDAO();
        try {
            
            
            if(image != null && (image.endsWith(".png") || image.endsWith(".jpg"))){
            //lay dinh dang png hoac jpg
            //dat du lieu vao doi tuong p(product)
            price = Double.parseDouble(price_raw);
            stock = Integer.parseInt(stock_raw);
            Product p = new Product();
            p.setName(name);
            p.setDescription(description);
            p.setCategory(category);
            p.setPrice(price);
            p.setStock(stock);
            p.setImage(image);
            pdao.addProduct(p); 
//lay ham addproduct trong ProductDAO de add doi tuong p vao database
            HttpSession session = request.getSession(); //tao session
            String message = (String) request.getSession().getAttribute("message"); //gan String message 
            session.removeAttribute(message); //remove 
            session.setAttribute("message", "Product was added successfully");
            //neu add product thanh cong thi mes se in ra
            response.sendRedirect("show-products"); // dieu huong 
            }else{
                request.setAttribute("error", "Type of image must be .png or .jpg");
                //truong hop sai dinh dang anh
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
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
