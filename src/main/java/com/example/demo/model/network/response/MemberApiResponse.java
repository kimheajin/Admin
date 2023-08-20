package com.example.demo.model.network.response;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.enumclass.MemberStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberApiResponse {

	private Long id;
	
	private String account;
	
	private String password;
	
	private MemberStatus status;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	private List<OrderGroupApiResponse> orderGroupApiResponseList;
	
}
