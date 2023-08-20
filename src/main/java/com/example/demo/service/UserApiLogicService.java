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
import com.example.demo.model.entity.Member;
import com.example.demo.model.enumclass.MemberStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.Pagination;
import com.example.demo.model.network.request.MemberApiRequest;
import com.example.demo.model.network.response.ItemApiResponse;
import com.example.demo.model.network.response.OrderGroupApiResponse;
import com.example.demo.model.network.response.MemberApiResponse;
import com.example.demo.model.network.response.MemberOrderinfoApiResponse;

@Service
public class UserApiLogicService extends BaseService<MemberApiRequest, MemberApiResponse, Member>{

	@Autowired
	private OrderGroupApiLogicService orderGroupApiLogicService;
	
	@Autowired
	private ItemApiLogicService itemApiLogicService;
	
	// 1. Request Data가져오기
	// 2. user 생성
	// 3. 생성된 데이터를 기준으로 UserApiResponse를 return
	@Override
	public Header<MemberApiResponse> create(Header<MemberApiRequest> request) {
		
		// 1.
		MemberApiRequest memberApiRequest = request.getData();
		
		
		// 2.
		Member user = Member.builder()
				.account(memberApiRequest.getAccount())
				.password(memberApiRequest.getPassword())
				.status(MemberStatus.REGISTERED)
				.phoneNumber(memberApiRequest.getPhoneNumber())
				.email(memberApiRequest.getEmail())
				.registeredAt(LocalDateTime.now())
				.build();
		
		Member newMember = baseRepository.save(user);
		
		// 3.
		
		return Header.OK(response(newMember));
	}

	@Override
	public Header<MemberApiResponse> read(Long id) {
		// id -> repository getOne, getById
		Optional<Member> optional = baseRepository.findById(id);
		
		// user -> userApiResponse return
		return optional.map(member -> response(member))
						.map(userApiResponse -> Header.OK(userApiResponse))
						.orElseGet(()->Header.ERROR("데이터 없음"));
		// orElseGet은 취득한 데이터가 없는 경우 사용한다. 
	}

	@Override
	public Header<MemberApiResponse> update(Header<MemberApiRequest> request) {
		
		// 1. data
		MemberApiRequest memberApiRequest = request.getData();
		
		// 2. id -> member 데이터 찾음
		Optional<Member> optional1 = baseRepository.findById(memberApiRequest.getId());
		
		return optional1.map(member -> {
			// 3. data->update
			// id에 대한 업데이트 발생
			member.setAccount(memberApiRequest.getAccount())
							.setPassword(memberApiRequest.getPassword())
							.setStatus(memberApiRequest.getStatus())
							.setPhoneNumber(memberApiRequest.getPhoneNumber())
							.setEmail(memberApiRequest.getEmail())
							.setRegisteredAt(memberApiRequest.getRegisteredAt())
							.setUnregisteredAt(memberApiRequest.getUnregisteredAt());
		
			return member;
		})
				// 4. memberApiResponse
		.map(member->baseRepository.save(member)) // update -> newmember 새로운 유저 객체 반환.
		.map(updateMember->response(updateMember)) // response를 통해 userApiResponse로 리턴하게 된다.
		.map(userApiResponse -> Header.OK(userApiResponse))
		// .map(Header::OK)
		.orElseGet(()->Header.ERROR("데이터 없음"));
		
	}

	@Override
	public Header delete(Long id) {
		// 1. id -> repository -> member 취득
		Optional<Member> optional = baseRepository.findById(id);
		
		// 2. repository -> delete 삭제 실행
		// response return
		return optional.map(member ->{
			baseRepository.delete(member);
			return Header.OK();
		})
		.orElseGet(()->Header.ERROR("데이터 없음"));
	}
	
	private MemberApiResponse response(Member member){
		// member -> memberApiResponse
		
		MemberApiResponse memberApiResponse = MemberApiResponse.builder()
				.id(member.getId())
				.account(member.getAccount())
				.password(member.getPassword())
				.email(member.getEmail())
				.phoneNumber(member.getPhoneNumber())
				.status(member.getStatus())
				.registeredAt(member.getRegisteredAt())
				.unregisteredAt(member.getUnregisteredAt())
				.build();
		
		//Header + data return
		
		return memberApiResponse;
	}
	
	public Header<List<MemberApiResponse>> search(Pageable pageable){
		// pageable에 해당되는 유저들을 page라는것을 통해 
		// 유저를 하나씩 뽑아 response(member)로 하여금 리스트로 변환시켜 Header에 적용
		Page<Member> members = baseRepository.findAll(pageable);
		
		List<MemberApiResponse> memberApiResponseList = members.stream()
													.map(member -> response(member))
													.collect(Collectors.toList());
		
		Pagination pagination = Pagination.builder()
										.totalPages(members.getTotalPages())
										.totalElements(members.getTotalElements())
										.currentPage(members.getNumber())
										.currentElements(members.getNumberOfElements())
										.build();
		
		return Header.OK(memberApiResponseList, pagination);
	}
	
	public Header<MemberOrderinfoApiResponse> orderInfo(Long id){
		
		// 필요정보
		// member
		Member member = baseRepository.getById(id);
		
		MemberApiResponse memberApiResponse = response(member);
		
		memberApiResponse.setOrderGroupApiResponseList(null);
		
		// orderGroup
		List<OrderGroup> orderGroupList = member.getOrderGroupList();
		
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
		
		memberApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);
		
		MemberOrderinfoApiResponse memberOrderInfoApiResponse = MemberOrderinfoApiResponse.builder()
				.memberApiResponse(memberApiResponse)
				.build();
		
		return Header.OK(memberOrderInfoApiResponse);
	}

}
