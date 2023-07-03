/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.Grade;
import model.Students;
import repository.GradeRepository;
import repository.StudentsRepository;
import service.GradeService;

/**
 *
 * @author Đức Hiệu
 */
public class GradeServiceImpl implements GradeService {

    private GradeRepository gradeRes = new GradeRepository();
    private StudentsRepository stRes = new StudentsRepository();

    @Override
    public List<Grade> getAll() {
        return gradeRes.getAll();
    }

    @Override
    public String add(Grade g) {
        Students stMa = stRes.getOne(g.getStudent().getMaSV());
        if (g.getStudent().getMaSV().isEmpty()) {
            return "Mã SV không được để trống";
        }
        if (stMa == null) {
            return "Mã SV không tồn tại";
        }
        if (gradeRes.checkTimMaSVKhiThemGrade(g.getStudent().getMaSV()) == true) {
            return "Mã SV trùng (đã có điểm), vui lòng nhập lại";
        }
        if (g.getTiengAnh() > 10 || g.getTiengAnh() < 0) {
            return "Điểm tiếng anh phải nhỏ hơn hoặc bằng 10";
        }
        if (g.getTinHoc() > 10 || g.getTinHoc() < 0) {
            return "Điểm tin học phải nhỏ hơn hoặc bằng 10";
        }
        if (g.getGdtc() > 10 || g.getGdtc() < 0) {
            return "Điểm GDTC phải nhỏ hơn hoặc bằng 10";
        }
        boolean add = gradeRes.add(g);
        if (add) {
            return "Lưu thành công";
        } else {
            return "Lưu thất bại";
        }
    }

    @Override
    public String update(Grade g, String maSV) {
        boolean update = gradeRes.update(g, maSV);
        if (update) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(String maSV) {
        boolean delete = gradeRes.delete(maSV);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public List<Grade> searchByMa(String maSV) {
        return gradeRes.searchByMaSV(maSV);
    }

    @Override
    public List<Grade> chuyenTrang(int index) {
        return gradeRes.chuyenTrang(index);
    }

    @Override
    public boolean checkTimMaSVKhiThemGrade(String maSV) {
        return gradeRes.checkTimMaSVKhiThemGrade(maSV);
    }

    @Override
    public String checkInt(String tiengA, String tinH, String gdtc) {
        String result = "";
        if (tiengA.isEmpty()) {
            result = "Điểm tiếng anh trống";
        }
        if (tinH.isEmpty()) {
            result = "Điểm tin học trống";
        }
        if (gdtc.isEmpty()) {
            result = "Điểm GDTC trống";
        }
        if (tiengA.matches("^[A-Za-z]+$") || tinH.matches("^[A-Za-z]+$") || gdtc.matches("^[A-Za-z]+$")) {
            result = "Điểm sai định dạng";
        }
        return result;
    }
}
