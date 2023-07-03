/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.Students;
import repository.StudentsRepository;
import service.StudentsService;

/**
 *
 * @author Đức Hiệu
 */
public class StudentsServiceImpl implements StudentsService {

    private StudentsRepository studentRes = new StudentsRepository();

    @Override
    public List<Students> getAll() {
        return studentRes.getAll();
    }

    @Override
    public String add(Students st, List<Students> listStudents) {
        Students stCheck = studentRes.getOne(st.getMaSV());
        if (st.getMaSV().isEmpty()) {
            return "Mã sinh viên trống";
        }
        if (stCheck != null) {
            return "Mã sinh viên đã bị trùng, vui lòng nhập lại";
        }
        if (st.getHoTen().isEmpty()) {
            return "Họ tên không được để trống";
        }
        if (st.getHoTen().matches("^[0-9]+$")) {
            return "Họ tên sai định dạng";
        }
        if (st.getEmail().isEmpty()) {
            return "Email không được để trống";
        }
        if (st.getSdt().isEmpty()) {
            return "Số ĐT không được để trống";
        }
        if (st.getSdt().length() != 10) {
            return "Số ĐT sai định dạng";
        }
        if (st.getDiaChi().isEmpty()) {
            return "Địa chỉ không được để trống";
        }
        boolean add = studentRes.add(st);
        if (add) {
            return "Lưu thành công";
        } else {
            return "Lưu thất bại";

        }
    }

    @Override
    public String update(Students st, String maSV, List<Students> listStudents) {
        if (st.getHoTen().isEmpty()) {
            return "Họ tên không được để trống";
        } else if (st.getHoTen().matches("^[0-9]+$")) {
            return "Họ tên sai định dạng";
        } else if (st.getEmail().isEmpty()) {
            return "Email không được để trống";
        } else if (st.getSdt().isEmpty()) {
            return "Số ĐT không được để trống";
        } else if (st.getSdt().length() != 10) {
            return "Số ĐT sai định dạng";
        } else if (st.getDiaChi().isEmpty()) {
            return "Địa chỉ không được để trống";
        } else {
            boolean update = studentRes.update(st, maSV);
            if (update) {
                return "Sửa thành công";
            } else {
                return "Sửa thất bại";
            }
        }
    }

    @Override
    public String delete(String maSV) {
        studentRes.deleteGrade(maSV);
        boolean delete = studentRes.delete(maSV);
        if (delete) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public Students getOne(String maSV) {
        return studentRes.getOne(maSV);
    }
}
