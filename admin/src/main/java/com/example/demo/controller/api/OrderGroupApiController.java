package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.network.request.OrderGroupApiRequest;
import com.example.demo.model.network.response.OrderGroupApiResponse;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup>{

//	@Autowired
//	private OrderGroupApiLogicService orderGroupApiLogicService; 
//	
//	@PostConstruct
//	public void init() {
//		this.baseService = orderGroupApiLogicService;
//	}
}
