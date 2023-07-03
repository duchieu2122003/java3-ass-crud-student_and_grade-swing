/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Đức Hiệu
 */
public class Grade {

    private int id;
    private Students student;
    private int tiengAnh;
    private int tinHoc;
    private int gdtc;

    public Grade() {
    }

    public Grade(int id, Students student, int tiengAnh, int tinHoc, int gdtc) {
        this.id = id;
        this.student = student;
        this.tiengAnh = tiengAnh;
        this.tinHoc = tinHoc;
        this.gdtc = gdtc;
    }

    public Grade(Students student, int tiengAnh, int tinHoc, int gdtc) {
        this.student = student;
        this.tiengAnh = tiengAnh;
        this.tinHoc = tinHoc;
        this.gdtc = gdtc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public int getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(int tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public int getTinHoc() {
        return tinHoc;
    }

    public void setTinHoc(int tinHoc) {
        this.tinHoc = tinHoc;
    }

    public int getGdtc() {
        return gdtc;
    }

    public void setGdtc(int gdtc) {
        this.gdtc = gdtc;
    }

    public float tinhDiemTB() {
        float tong = (this.tiengAnh) + (this.tinHoc) + (this.gdtc);
        float trungBinh = (float) tong / 3;
        float tb = (float) (Math.ceil(Math.round(trungBinh * 100.0)) / (100.0));
        return tb;
    }

    public Object[] toDataGrade() {
        return new Object[]{student.getMaSV(), student.getHoTen(), tiengAnh, tinHoc, gdtc, (float) tinhDiemTB()};
    }
}
