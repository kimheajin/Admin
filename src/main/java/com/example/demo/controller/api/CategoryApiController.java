package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.Category;
import com.example.demo.model.network.request.CategoryApiRequest;
import com.example.demo.model.network.response.CategoryApiResponse;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends CrudController<CategoryApiRequest, CategoryApiResponse, Category>{

//	@Autowired
//	private CategoryApiLogicService categoryApiLogicService;
//	
//	@PostConstruct
//	public void init() {
//		this.baseService = categoryApiLogicService;
//	}

}
