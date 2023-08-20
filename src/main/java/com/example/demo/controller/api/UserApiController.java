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
import com.example.demo.model.entity.Member;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.MemberApiRequest;
import com.example.demo.model.network.response.MemberApiResponse;
import com.example.demo.model.network.response.MemberOrderinfoApiResponse;
import com.example.demo.service.UserApiLogicService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/user")
@Log4j2
public class UserApiController extends CrudController<MemberApiRequest, MemberApiResponse, Member> {

	@Autowired
	private UserApiLogicService userApiLogicService;
	
	@GetMapping("")
	public Header<List<MemberApiResponse>> search(@PageableDefault(sort="id",direction = Sort.Direction.ASC, size = 10) Pageable pageble){
		log.info("{}", pageble);
		return userApiLogicService.search(pageble);
	}
	
	@GetMapping("/{id}/orderInfo")
	public Header<MemberOrderinfoApiResponse> orderInfo(@PathVariable Long id){
		return userApiLogicService.orderInfo(id);
	}
}
