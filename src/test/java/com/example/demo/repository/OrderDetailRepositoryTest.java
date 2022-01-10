package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AdminApplicationTests;
import com.example.demo.model.entity.OrderDetail;

public class OrderDetailRepositoryTest extends AdminApplicationTests{
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Test
	public void create() {
		OrderDetail orderDetail = new OrderDetail();
		
		orderDetail.setStatus("WATTING");
		orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
		orderDetail.setQuantity(1);
		orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
		
//		orderDetail.setOrderGroupId(1L); // 장바구니
//		orderDetail.setItemId(1L); // 상품
		orderDetail.setCreatedAt(LocalDateTime.now());
		orderDetail.setCreatedBy("AdminServer");
		
		OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
		assertNotNull(newOrderDetail);
		
	}
	
//	@Test
//	public void read() {
//		OrderDetail orderDetail = orderDetailRepository.findById("1L");
//		
//		assertNotNull(orderDetail);
//		
//		orderDetail.getId();
////		orderDetail.getItemId();
//	}

}
