package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.Partner;
import com.example.demo.model.network.request.PartnerApiRequest;
import com.example.demo.model.network.response.PartnerApiResponse;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner>{

//	@Autowired
//	private PartnerApiLogicService partnerApiLogicService;
//	
//	@PostConstruct
//	public void init() {
//		this.baseService = partnerApiLogicService;
//	}

}
