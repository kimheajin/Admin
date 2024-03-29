package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.OrderDetail;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.OrderDetailApiRequest;
import com.example.demo.model.network.response.OrderDetailApiResponse;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderGroupRepository;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail>{
	
	@Autowired
	private OrderGroupRepository orderGroupRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
		
		OrderDetailApiRequest body = request.getData();
		
		OrderDetail orderDetail = OrderDetail.builder()
											.status(body.getStatus())
											.arrivalDate(body.getArrivalDate())
											.quantity(body.getQuantity())
											.totalPrice(body.getTotalPrice())
											.orderGroup(orderGroupRepository.getById(body.getOrderGroupId()))
											.item(itemRepository.getById(body.getItemId()))
											.build();
		
		OrderDetail newOrderDetail = baseRepository.save(orderDetail);
		
		return response(newOrderDetail);
	}

	@Override
	public Header<OrderDetailApiResponse> read(Long id) {
		return baseRepository.findById(id)
							.map(orderDetail->response(orderDetail))
							.orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
		OrderDetailApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
							.map(EntityOrderDetail -> {
								EntityOrderDetail.setStatus(body.getStatus())
												 .setArrivalDate(body.getArrivalDate())
												 .setQuantity(body.getQuantity())
												 .setTotalPrice(body.getTotalPrice())
												 .setOrderGroup(orderGroupRepository.getById(body.getOrderGroupId()))
												 .setItem(itemRepository.getById(body.getItemId()));
								
								return EntityOrderDetail;
							})
							.map(newOrderDetail->baseRepository.save(newOrderDetail))
							.map(orderDetail -> response(orderDetail))
							.orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
							.map(orderDetail->{
								baseRepository.delete(orderDetail);
								return Header.OK();
							})
							.orElseGet(()->Header.ERROR("데이터 없음"));
	}

	private Header<OrderDetailApiResponse> response(OrderDetail orderDetail){
		
		OrderDetailApiResponse body = OrderDetailApiResponse.builder()
										.status(orderDetail.getStatus())
										.arrivalDate(orderDetail.getArrivalDate())
										.quantity(orderDetail.getQuantity())
										.totalPrice(orderDetail.getTotalPrice())
										.orderGroupId(orderDetail.getOrderGroup().getId())
										.itemId(orderDetail.getItem().getId())
										.build();
										
		return Header.OK(body);
	}
}
