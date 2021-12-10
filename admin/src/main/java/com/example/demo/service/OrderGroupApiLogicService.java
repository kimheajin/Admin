package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.enumclass.OrderType;
import com.example.demo.model.enumclass.OrderPaymentType;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.OrderGroupApiRequest;
import com.example.demo.model.network.response.OrderGroupApiResponse;
import com.example.demo.repository.OrderGroupRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
		
		OrderGroupApiRequest body = request.getData();
		
		OrderGroup orderGroup = OrderGroup.builder()
				.status(body.getStatus())
				.orderType(body.getOrderType())
				.revAddress(body.getRevAddress())
				.revName(body.getRevName())
				.paymentType(body.getPaymentType())
				.totalPrice(body.getTotalPrice())
				.totalQuantity(body.getTotalQuantity())
				.orderAt(body.getOrderAt())
				.arrivalDate(body.getArrivalDate())
				.user(userRepository.getById(body.getUserId()))
				.build();
		
		OrderGroup newOrderGroup = baseRepository.save(orderGroup);
		
		return response(newOrderGroup);
	}

	@Override
	public Header<OrderGroupApiResponse> read(Long id) {
		return baseRepository.findById(id)
							.map(orderGroup->response(orderGroup))
							// .map(this::response) // 이런 형식도 적용 가능.
							.orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
		OrderGroupApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
							.map(EntityorderGroup -> {
								EntityorderGroup.setStatus(body.getStatus())
										.setOrderType(body.getOrderType())
										.setRevAddress(body.getRevAddress())
										.setRevName(body.getRevName())
										.setPaymentType(body.getPaymentType())
										.setTotalPrice(body.getTotalPrice())
										.setTotalQuantity(body.getTotalQuantity())
										.setOrderAt(body.getOrderAt())
										.setArrivalDate(body.getArrivalDate())
										.setUser(userRepository.getById(body.getUserId()));
							
								return EntityorderGroup;
							})
							.map(newOrderGroup -> baseRepository.save(newOrderGroup))
							// .map(orderGroup->response(orderGroup))
							.map(this::response)
							.orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		
		return baseRepository.findById(id)
				.map(orderGroup -> {
					baseRepository.delete(orderGroup);
					return Header.OK();
				})
				.orElseGet(() -> Header.ERROR("데이터 없음"));
	}
	
	public Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
		
		OrderGroupApiResponse body = OrderGroupApiResponse.builder()
				.id(orderGroup.getId())
				.status(orderGroup.getStatus())
				.orderType(orderGroup.getOrderType())
				.revAddress(orderGroup.getRevAddress())
				.revName(orderGroup.getRevName())
				.paymentType(orderGroup.getPaymentType())
				.totalPrice(orderGroup.getTotalPrice())
				.totalQuantity(orderGroup.getTotalQuantity())
				.orderAt(orderGroup.getOrderAt())
				.arrivalDate(orderGroup.getArrivalDate())
				.userId(orderGroup.getUser().getId())
				.build();
				
		return Header.OK(body);
	}
}
