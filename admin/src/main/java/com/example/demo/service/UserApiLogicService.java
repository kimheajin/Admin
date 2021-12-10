package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.entity.User;
import com.example.demo.model.enumclass.UserStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.Pagination;
import com.example.demo.model.network.request.UserApiRequest;
import com.example.demo.model.network.response.ItemApiResponse;
import com.example.demo.model.network.response.OrderGroupApiResponse;
import com.example.demo.model.network.response.UserApiResponse;
import com.example.demo.model.network.response.UserOrderinfoApiResponse;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User>{

	@Autowired
	private OrderGroupApiLogicService orderGroupApiLogicService;
	
	@Autowired
	private ItemApiLogicService itemApiLogicService;
	
	// 1. Request Data가져오기
	// 2. user 생성
	// 3. 생성된 데이터를 기준으로 UserApiResponse를 return
	@Override
	public Header<UserApiResponse> create(Header<UserApiRequest> request) {
		
		// 1.
		UserApiRequest userApiRequest = request.getData();
		
		
		// 2.
		User user = User.builder()
				.account(userApiRequest.getAccount())
				.password(userApiRequest.getPassword())
				.status(UserStatus.REGISTERED)
				.phoneNumber(userApiRequest.getPhoneNumber())
				.email(userApiRequest.getEmail())
				.registeredAt(LocalDateTime.now())
				.build();
		
		User newUser = baseRepository.save(user);
		
		// 3.
		
		return Header.OK(response(newUser));
	}

	@Override
	public Header<UserApiResponse> read(Long id) {
		// id -> repository getOne, getById
		Optional<User> optional = baseRepository.findById(id);
		
		// user -> userApiResponse return
		return optional.map(user -> response(user))
						.map(userApiResponse -> Header.OK(userApiResponse))
						.orElseGet(()->Header.ERROR("데이터 없음"));
		// orElseGet은 취득한 데이터가 없는 경우 사용한다. 
	}

	@Override
	public Header<UserApiResponse> update(Header<UserApiRequest> request) {
		
		// 1. data
		UserApiRequest userApiRequest = request.getData();
		
		// 2. id -> user 데이터 찾음
		Optional<User> optional1 = baseRepository.findById(userApiRequest.getId());
		
		return optional1.map(user -> {
			// 3. data->update
			// id에 대한 업데이트 발생
			user.setAccount(userApiRequest.getAccount())
							.setPassword(userApiRequest.getPassword())
							.setStatus(userApiRequest.getStatus())
							.setPhoneNumber(userApiRequest.getPhoneNumber())
							.setEmail(userApiRequest.getEmail())
							.setRegisteredAt(userApiRequest.getRegisteredAt())
							.setUnregisteredAt(userApiRequest.getUnregisteredAt());
		
			return user;
		})
				// 4. userApiResponse
		.map(user->baseRepository.save(user)) // update -> newUser 새로운 유저 객체 반환.
		.map(updateUser->response(updateUser)) // response를 통해 userApiResponse로 리턴하게 된다.
		.map(userApiResponse -> Header.OK(userApiResponse))
		// .map(Header::OK)
		.orElseGet(()->Header.ERROR("데이터 없음"));
		
	}

	@Override
	public Header delete(Long id) {
		// 1. id -> repository -> user 취득
		Optional<User> optional = baseRepository.findById(id);
		
		// 2. repository -> delete 삭제 실행
		// response return
		return optional.map(user ->{
			baseRepository.delete(user);
			return Header.OK();
		})
		.orElseGet(()->Header.ERROR("데이터 없음"));
	}
	
	private UserApiResponse response(User user){
		// user -> userApiResponse
		
		UserApiResponse userApiResponse = UserApiResponse.builder()
				.id(user.getId())
				.account(user.getAccount())
				.password(user.getPassword())
				.email(user.getEmail())
				.phoneNumber(user.getPhoneNumber())
				.status(user.getStatus())
				.registeredAt(user.getRegisteredAt())
				.unregisteredAt(user.getUnregisteredAt())
				.build();
		
		//Header + data return
		
		return userApiResponse;
	}
	
	public Header<List<UserApiResponse>> search(Pageable pageable){
		// pageable에 해당되는 유저들을 page라는것을 통해 
		// 유저를 하나씩 뽑아 response(user)로 하여금 리스트로 변환시켜 Header에 적용
		Page<User> users = baseRepository.findAll(pageable);
		
		List<UserApiResponse> userApiResponseList = users.stream()
													.map(user -> response(user))
													.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
										.totalPages(users.getTotalPages())
										.totalElements(users.getTotalElements())
										.currentPage(users.getNumber())
										.currentElements(users.getNumberOfElements())
										.build();
		
		return Header.OK(userApiResponseList, pagination);
	}
	
	public Header<UserOrderinfoApiResponse> orderInfo(Long id){
		
		// 필요정보
		// user
		User user = baseRepository.getById(id);
		
		UserApiResponse userApiResponse = response(user);
		
		userApiResponse.setOrderGroupApiResponseList(null);
		
		// orderGroup
		List<OrderGroup> orderGroupList = user.getOrderGroupList();
		
		List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
				.map(orderGroup -> {
					OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
					
					List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
							.map(detail -> detail.getItem())
							.map(item -> itemApiLogicService.response(item).getData())
							.collect(Collectors.toList());
					
					orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
					return orderGroupApiResponse;
				 })
				  .collect(Collectors.toList());
		
		userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
		
		UserOrderinfoApiResponse userOrderInfoApiResponse = UserOrderinfoApiResponse.builder()
				.userApiResponse(userApiResponse)
				.build();
		
		return Header.OK(userOrderInfoApiResponse);
	}

}
