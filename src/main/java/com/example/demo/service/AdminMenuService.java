package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.front.AdminMenu;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenu> getAdminMenu(){

        return Arrays.asList(
                AdminMenu.builder().title("사용자 등록").url("/register").code("register").build()
        );

    }

}
