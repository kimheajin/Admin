package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.Item;
import com.example.demo.model.network.request.ItemApiRequest;
import com.example.demo.model.network.response.ItemApiResponse;


@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item>{

//	@Autowired
//	private ItemApiLogicService itemApiLogicService;
//	
//	@PostConstruct
//	public void init() {
//		this.baseService = itemApiLogicService;
//	}
	
}
