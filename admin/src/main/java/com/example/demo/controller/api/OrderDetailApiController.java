package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.OrderDetail;
import com.example.demo.model.network.request.OrderDetailApiRequest;
import com.example.demo.model.network.response.OrderDetailApiResponse;

@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController extends CrudController<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail>{

//	@Autowired
//	private OrderDetailApiLogicService orderDetailApiLogicService;
//	
//	@PostConstruct
//	public void init() {
//		this.baseService = orderDetailApiLogicService;
//	}
}
