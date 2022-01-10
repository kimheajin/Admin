package com.example.demo.model.network.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserApiRequest {

	private Long id;
	
	private String account;
	
	private String password;
	
	private String status;
	
	private String role;
	
	private LocalDateTime lastLoginAt;
	
	private Integer loginFailCount;

	private LocalDateTime passwordUpdatedAt;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
	private LocalDateTime lostLoginAt;
}