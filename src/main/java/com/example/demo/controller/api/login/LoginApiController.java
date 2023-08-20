package com.example.demo.controller.api.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.SearchParam;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.MemberApiLogicService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginApiController {

	private MemberApiLogicService memberApiLogicService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/login/action")
	public Long postMethod(@RequestBody UserDto userDto) {
		return memberApiLogicService.signUp(userDto);
	}
	
//	@GetMapping("/login/register")
//	public String signupForm(Model model) {
//		model.addAttribute("user", new UserDto(null, null, null));
//		return null;
//		
//	}
}

