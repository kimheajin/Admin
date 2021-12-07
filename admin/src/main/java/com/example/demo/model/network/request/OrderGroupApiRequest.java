package com.example.demo.model.network.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.model.enumclass.OrderType;
import com.example.demo.model.enumclass.OrderPaymentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiRequest {
	
	private Long id;
	
	private String status;
	
	private OrderType orderType;
	
	private String revAddress;
	
	private String revName;
	
//	private OrderPaymentType paymentType;
	private String paymentType;
	
	private BigDecimal totalPrice;
	
	private Integer totalQuantity;
	
	private LocalDateTime orderAt;
	
	private LocalDateTime arrivalDate;
	
	private Long userId;
}
