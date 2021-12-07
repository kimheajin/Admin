package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SearchParam;

@RestController
@RequestMapping("/api")
public class PostController {
	
	/* POST형식이 사용되는 곳
	/* HTML <Form>
	/* Ajax 검색
	 * http post의 body에 데이터를 집어넣어 보내기 때문에, RequestBody를 이용해 매개변수를 받아야한다.
	 */
	
//	@RequestMapping(method = RequestMethod.POST, path = "/postMethod") // 아래와 동일한 동작이다.
	@PostMapping("/postMethod")
	public SearchParam postMethod(@RequestBody SearchParam searchParam) {
		return searchParam;
	}
	
	
}
