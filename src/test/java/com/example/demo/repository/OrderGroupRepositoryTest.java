package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AdminApplicationTests;
import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.enumclass.OrderType;
import com.example.demo.model.enumclass.OrderPaymentType;

public class OrderGroupRepositoryTest extends AdminApplicationTests{

	@Autowired
	private OrderGroupRepository orderGroupRepository;
	
	@Test
	public void create() {
		OrderGroup orderGroup = new OrderGroup();
		orderGroup.setStatus("COMPLETE");
		orderGroup.setOrderType(OrderType.ALL);
		orderGroup.setRevAddress("서울시 강남구");
		orderGroup.setRevName("이강남");
		orderGroup.setPaymentType("일시불");
		orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
		orderGroup.setTotalQuantity(1);
		orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
		orderGroup.setArrivalDate(LocalDateTime.now());
		orderGroup.setCreatedAt(LocalDateTime.now());
		orderGroup.setCreatedBy("AdminServer");
//		orderGroup.setUserId(1L);
		
		OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
		
		assertNotNull(newOrderGroup);
	}
}
