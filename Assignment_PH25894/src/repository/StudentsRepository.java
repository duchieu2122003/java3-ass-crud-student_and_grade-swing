/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.List;
import model.Students;
import jdbc.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Đức Hiệu
 */
public class StudentsRepository {

    public List<Students> getAll() {
        String query = "SELECT MASV, Hoten, Email, SoDT, Gioitinh, Diachi, Hinh FROM dbo.STUDENTS";
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            List<Students> listStudent = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listStudent.add(new Students(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7)));
            }
            return listStudent;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Students getOne(String maSV) {
        String query = "SELECT MASV, Hoten, Email, SoDT, Gioitinh, Diachi, Hinh FROM dbo.STUDENTS WHERE MASV = ? ";
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maSV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Students(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(Students st) {
        String query = "INSERT INTO [dbo].[STUDENTS]"
                + "([MASV],[Hoten] ,[Email],[SoDT],[Gioitinh],[Diachi],[Hinh])"
                + " VALUES(?,?,?,?,?,?,?)";
        int check = 0;
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, st.getMaSV());
            ps.setObject(2, st.getHoTen());
            ps.setObject(3, st.getEmail());
            ps.setObject(4, st.getSdt());
            ps.setObject(5, st.isGioiTinh());
            ps.setObject(6, st.getDiaChi());
            ps.setObject(7, st.getHinh());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(Students st, String maSV) {
        String query = "UPDATE [dbo].[STUDENTS]"
                + " SET [Hoten] = ?,[Email] = ?,[SoDT] = ?,[Gioitinh] = ?,[Diachi] = ?,[Hinh] = ?"
                + " WHERE MASV = ?";
        int check = 0;
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, st.getHoTen());
            ps.setObject(2, st.getEmail());
            ps.setObject(3, st.getSdt());
            ps.setObject(4, st.isGioiTinh());
            ps.setObject(5, st.getDiaChi());
            ps.setObject(6, st.getHinh());
            ps.setObject(7, maSV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean delete(String maSV) {
        String query = "DELETE FROM [dbo].[STUDENTS] WHERE [MASV]  = ? ";
        int check = 0;
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maSV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public void deleteGrade(String maSV) {
        String query = "DELETE FROM [dbo].[GRADE] WHERE MASV = ?";
        int check = 0;
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maSV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
