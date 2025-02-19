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
import model.Order;

/**
 *
 * @author Admin
 */
public class OrderDAO extends DBContext {

    public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            String sql = "insert into orders (totalprice, address, note, date, uid) values(?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, model.getTotalprice());
            st.setString(2, model.getAddress());
            st.setString(3, model.getNote());
            st.setString(4, model.getDate());
            st.setInt(5, model.getUser().getId());
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
        }
        
        return result;
    }
    
    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        String sql = "select * from orders where uid=? order by oid desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                UserDAO udao = new UserDAO();
                o.setOid(rs.getInt("oid"));
                o.setTotalprice(rs.getDouble("totalprice"));
                o.setAddress(rs.getString("address"));
                o.setNote(rs.getString("note"));
                o.setDate(rs.getString("date"));
                o.setUser(udao.getUserById(rs.getInt("uid")));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public void cancelOrder(int id) {
        try {
            String sql = "delete from orders where oid=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public Order getLastOrder() {
        try {
            //lay don hang moi nhat 
            String sql = "select top 1 * from orders order by oid desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                UserDAO udao = new UserDAO();
                o.setOid(rs.getInt("oid"));
                o.setTotalprice(rs.getDouble("totalprice"));
                o.setAddress(rs.getString("address"));
                o.setNote(rs.getString("note"));
                o.setDate(rs.getString("date"));
                o.setUser(udao.getUserById(rs.getInt("uid")));
                return o;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Order getSingleOrder(int id) {
        Order p = null;
        try {
            String sql = "select * from orders where oid=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p = new Order();
                UserDAO udao = new UserDAO();
                p.setOid(rs.getInt("oid"));
                p.setTotalprice(rs.getDouble("totalprice"));
                p.setAddress(rs.getString("address"));
                p.setNote(rs.getString("note"));
                p.setDate(rs.getString("date"));
                p.setUser(udao.getUserById(rs.getInt("uid")));
            }
        } catch (SQLException e) {
        }
        return p;
    }
}
