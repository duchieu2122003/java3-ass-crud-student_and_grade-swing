/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLConnection;

/**
 *
 * @author Đức Hiệu
 */
public class UserRepository {

    public List<Users> getAll() {
        String query = "SELECT username, password, role FROM dbo.USERS";
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            List<Users> listUser = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listUser.add(new Users(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listUser;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Users getOne(String userName) {
        String query = "SELECT username, password, role FROM dbo.USERS WHERE username =? ";
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Users(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
