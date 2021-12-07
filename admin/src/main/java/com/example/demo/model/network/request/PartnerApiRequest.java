package com.example.demo.model.network.request;

import java.time.LocalDateTime;

import com.example.demo.model.enumclass.PartnerStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiRequest {

	private Long id;
	
	private String name;
	
	private PartnerStatus status;
	
	private String address;
	
	private String callCenter;
	
	private String partnerNumber;
	
	private String businessNumber;
	
	private String ceoName;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	private Long categoryId;
}
