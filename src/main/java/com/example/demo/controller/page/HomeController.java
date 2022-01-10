package com.example.demo.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.AdminMenuService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
    private AdminMenuService adminMenuService;
	
	@RequestMapping(path = {""})
    public ModelAndView index() {
        return new ModelAndView("index")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "main")
                ;
    }
	
	@RequestMapping("/register")
    public ModelAndView user() {
        return new ModelAndView("/login/register")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "register")
                ;
    }
}
