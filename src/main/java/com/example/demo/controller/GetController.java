package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SearchParam;
import com.example.demo.model.network.Header;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

	@RequestMapping(method = RequestMethod.GET, path="/getMethod") // localhost:8080/api/getMethod
	public String getRequest() {
		return "Hi getMethod";
	}
	
	// GetMapping은 주소만 지정
	@GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
	// @RequestParam에 name을 지정하면, 해당 name의 이름으로 받아오되, 메소드의 변수로 pwd를 쓴다. 라는 말이 됨.
	public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
		String password = "bbbb";
		System.out.println("id : "+id + "\npassword : " + password);
		return id+password;
	}
	
	// localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
	@GetMapping("/getMultiParameter")
	public SearchParam getMultiParameter(SearchParam searchParam) {
		System.out.println(searchParam.getAccount());
		System.out.println(searchParam.getEmail());
		System.out.println(searchParam.getPage());
		
		// 객체 searchParam를 리턴하게 되면, Gradle의 jackson에 의해 json형식으로 리턴하게 된다.
		return searchParam;
	}
	
	@GetMapping("/header")
	public Header getHeader() {
		
		// {"resultCode" :"OK", "description" : "OK"}
		return Header.builder().resultCode("OK").description("OK").build();
	}
}
