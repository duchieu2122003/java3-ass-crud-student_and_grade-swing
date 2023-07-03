/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.List;
import model.Users;
import repository.UserRepository;
import service.UserService;

/**
 *
 * @author Đức Hiệu
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRes = new UserRepository();

    @Override
    public List<Users> getAll() {
        return userRes.getAll();
    }

    @Override
    public Users getOne(String userName) {
        return userRes.getOne(userName);
    }
}
