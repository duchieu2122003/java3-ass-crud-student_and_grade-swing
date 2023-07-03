/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLConnection;
import model.Grade;
import model.Students;

/**
 *
 * @author Đức Hiệu
 */
public class GradeRepository {

    public List<Grade> getAll() {
        String query = "SELECT dbo.GRADE.id,dbo.STUDENTS.MASV, dbo.STUDENTS.Hoten, dbo.GRADE.Tienganh, dbo.GRADE.Tinhoc, dbo.GRADE.GDTC,dbo.STUDENTS.Email, dbo.STUDENTS.SoDT, dbo.STUDENTS.Gioitinh, dbo.STUDENTS.Diachi, dbo.STUDENTS.Hinh "
                + " FROM dbo.GRADE INNER JOIN dbo.STUDENTS ON dbo.GRADE.MASV = dbo.STUDENTS.MASV "
                + " ORDER BY ((dbo.GRADE.Tienganh + dbo.GRADE.Tinhoc + dbo.GRADE.GDTC)/3) DESC "
                + " OFFSET 0 ROW FETCH NEXT 3 ROW ONLY";
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            List<Grade> listDiem = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Students student = new Students(rs.getString(2), rs.getString(3), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getString(10), rs.getString(11));
                listDiem.add(new Grade(rs.getInt(1), student, rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }
            return listDiem;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Grade> chuyenTrang(int index) {
        String query = "SELECT dbo.GRADE.id,dbo.STUDENTS.MASV, dbo.STUDENTS.Hoten, dbo.GRADE.Tienganh, dbo.GRADE.Tinhoc, dbo.GRADE.GDTC,dbo.STUDENTS.Email, dbo.STUDENTS.SoDT,dbo.STUDENTS.Gioitinh, dbo.STUDENTS.Diachi, dbo.STUDENTS.Hinh "
                + " FROM dbo.GRADE INNER JOIN dbo.STUDENTS ON dbo.GRADE.MASV = dbo.STUDENTS.MASV "
                + " ORDER BY ((dbo.GRADE.Tienganh + dbo.GRADE.Tinhoc + dbo.GRADE.GDTC)/3) DESC "
                + " OFFSET ? ROW FETCH NEXT 3 ROW ONLY";
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, index);
            List<Grade> listDiem = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Students student = new Students(rs.getString(2), rs.getString(3), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getString(10), rs.getString(11));
                listDiem.add(new Grade(rs.getInt(1), student, rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }
            return listDiem;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(Grade g) {
        String query = "INSERT INTO [dbo].[GRADE] ([MASV],[Tienganh],[Tinhoc],[GDTC])VALUES(?,?,?,?)";
        int check = 0;
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, g.getStudent().getMaSV());
            ps.setObject(2, g.getTiengAnh());
            ps.setObject(3, g.getTinHoc());
            ps.setObject(4, g.getGdtc());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(Grade g, String maSV) {
        String query = "UPDATE [dbo].[GRADE] "
                + "   SET [Tienganh] = ?,[Tinhoc] = ?,[GDTC] = ?"
                + " WHERE MASV = ?";
        int check = 0;
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, g.getTiengAnh());
            ps.setObject(2, g.getTinHoc());
            ps.setObject(3, g.getGdtc());
            ps.setObject(4, maSV);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean delete(String maSV) {
        String query = "DELETE FROM [dbo].[GRADE] WHERE MASV = ?";
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

    public List<Grade> searchByMaSV(String maSV) {
        String query = "SELECT dbo.GRADE.id,dbo.STUDENTS.MASV, dbo.STUDENTS.Hoten, dbo.GRADE.Tienganh, dbo.GRADE.Tinhoc, dbo.GRADE.GDTC,dbo.STUDENTS.Email, dbo.STUDENTS.SoDT, dbo.STUDENTS.Gioitinh, dbo.STUDENTS.Diachi, dbo.STUDENTS.Hinh\n"
                + "FROM dbo.GRADE INNER JOIN dbo.STUDENTS ON dbo.GRADE.MASV = dbo.STUDENTS.MASV WHERE dbo.GRADE.MASV = ?";
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maSV);
            List<Grade> listDiem = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Students student = new Students(rs.getString(2), rs.getString(3), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getString(10), rs.getString(11));
                listDiem.add(new Grade(rs.getInt(1), student, rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }
            return listDiem;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    //Hàm check mã sinh viên có điểm có trong DBGrade(nếu có thì ko cho thêm)
    public boolean checkTimMaSVKhiThemGrade(String maSV) {
        String query = "SELECT MASV FROM dbo.GRADE WHERE MASV = ? ";
        boolean check = false;
        try (Connection con = SQLConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maSV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return check;
    }
}
