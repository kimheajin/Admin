package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.AdminUser;
import com.example.demo.model.network.request.AdminUserApiRequest;
import com.example.demo.model.network.response.AdminUserApiResponse;

@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController extends CrudController<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

//	@Autowired
//	private AdminUserApiLogicService adminUserApiLogicService;
//	
//	@PostConstruct
//	public void init() {
//		this.baseService = adminUserApiLogicService;
//	}
}
