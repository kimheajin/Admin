package com.example.demo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.User;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.UserApiRequest;
import com.example.demo.model.network.response.UserApiResponse;
import com.example.demo.model.network.response.UserOrderinfoApiResponse;
import com.example.demo.service.UserApiLogicService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/user")
@Log4j2
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

	@Autowired
	private UserApiLogicService userApiLogicService;
//	
//	@PostConstruct
//	public void init() {
//		this.baseService = userApiLogicService;
//	}
	@GetMapping("")
	public Header<List<UserApiResponse>> search(@PageableDefault(sort="id",direction = Sort.Direction.ASC, size = 10) Pageable pageble){
		log.info("{}", pageble);
		return userApiLogicService.search(pageble);
	}
	
	@GetMapping("/{id}/orderInfo")
	public Header<UserOrderinfoApiResponse> orderInfo(@PathVariable Long id){
		return userApiLogicService.orderInfo(id);
	}
}
