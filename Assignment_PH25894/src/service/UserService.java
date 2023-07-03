/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Users;

/**
 *
 * @author Đức Hiệu
 */
public interface UserService {

    List<Users> getAll();

    Users getOne(String userName);
}
