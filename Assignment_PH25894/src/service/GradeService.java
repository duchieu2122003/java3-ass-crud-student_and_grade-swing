/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Grade;

/**
 *
 * @author Đức Hiệu
 */
public interface GradeService {

    List<Grade> getAll();

    String add(Grade g);

    String update(Grade g, String maSV);

    String delete(String maSV);

    List<Grade> searchByMa(String maSV);

    List<Grade> chuyenTrang(int index);

    boolean checkTimMaSVKhiThemGrade(String maSV);

    String checkInt(String tiengA, String tinH, String gdtc);
}
