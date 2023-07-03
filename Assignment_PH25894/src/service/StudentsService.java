/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Students;

/**
 *
 * @author Đức Hiệu
 */
public interface StudentsService {

    List<Students> getAll();

    String add(Students st, List<Students> listStudents);

    String update(Students st, String maSV, List<Students> listStudents);

    String delete(String maSV);

    Students getOne(String maSV);
}
