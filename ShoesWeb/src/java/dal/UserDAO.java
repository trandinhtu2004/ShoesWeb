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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDAO extends DBContext {

    public User userLogin(String email, String password) {
        String sql = "select * from users "
                + "where [email] = ? and [password] = ?";
        User user = null;
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRoleid(rs.getInt("roleid"));
                user.setPhone(rs.getString("phone"));
            }
            System.out.println(user.toString());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return user;
    }

    public User getUserById(int id) {
        String sql = "select * from users where id=?";
        User user = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRoleid(rs.getInt("roleid"));
                user.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void addUser(User user) {
        try {
            String sql = "insert into users (fullname, email, password, roleid, phone) values(?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setInt(4, 0);
            st.setString(5, user.getPhone());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getTotalUsers() {
        String sql = "select count(*) from users";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "select * from users";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User p = new User();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("fullname"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));
                p.setRoleid(rs.getInt("roleid"));
                p.setPhone(rs.getString("phone"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public User getUserByEmail(String email) {
        String sql = "select * from users where email like ?";
        User user = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + email + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRoleid(rs.getInt("roleid"));
                user.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void addUserAdmin(User user) {
        try {
            String sql = "insert into users (fullname, email, password, roleid, phone) values(?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setInt(4, user.getRoleid());
            st.setString(5, user.getPhone());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateUser(User c) {
        String sql = "update users set fullname=?, email=?, password=?, roleid=?, phone=? where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setString(2, c.getEmail());
            st.setString(3, c.getPassword());
            st.setInt(4, c.getRoleid());
            st.setString(5, c.getPhone());
            st.setInt(6, c.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(int id) {
        String sql = "delete from users where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //giong pageing product, lay index tu url, bo qua
    public List<User> pagingUsers(int index) {
        List<User> list = new ArrayList<>();
        String sql = "select * from users\n"
                + "order by id\n"
                + "offset ? rows fetch next 6 rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("roleid"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int getFirstAdminID() {
        int firstAdminId = -1; // Giá trị mặc định nếu không tìm thấy admin
        String sql = "SELECT TOP 1 id FROM users WHERE roleid = 1 ORDER BY id ASC"; 
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                firstAdminId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return firstAdminId;
    }

}
