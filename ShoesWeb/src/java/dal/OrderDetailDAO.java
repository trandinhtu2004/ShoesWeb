/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrdersDetails;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO extends DBContext {
    
    public void insertOrderDetail(OrdersDetails od) {
        try {
            String sql = "insert into orders_details (oid, pid, price, quantity) values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, od.getOrder().getOid());
            st.setInt(2, od.getProduct().getId());
            st.setDouble(3, od.getPrice());
            st.setInt(4, od.getQuantity());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public List<OrdersDetails> listOrderDetail(int id) {
        List<OrdersDetails> list = new ArrayList<>();
        OrderDAO odao = new OrderDAO();
        ProductDAO pdao = new ProductDAO();
        String sql = "select * from orders_details where oid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrdersDetails o = new OrdersDetails();
                o.setOrder(odao.getSingleOrder(rs.getInt("oid")));
                o.setProduct(pdao.getSingleProduct(rs.getInt("pid")));
                o.setPrice(rs.getDouble("price"));
                o.setQuantity(rs.getInt("quantity"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public void cancelOrderDetail(int id) {
        try {
            String sql = "delete from orders_details where oid=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
