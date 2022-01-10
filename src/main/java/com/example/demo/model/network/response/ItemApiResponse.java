package com.example.demo.model.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.model.enumclass.ItemStatus;
import com.example.demo.model.network.request.ItemApiRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemApiResponse {

	private Long id;
	
	private ItemStatus status;
	
	private String name;
	
	private String title;
	
	private String content;
	
	private BigDecimal price;
	
	private String brandName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	private Long partnerId;
}
