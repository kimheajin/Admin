package com.example.demo.model.network.request;

import java.time.LocalDateTime;

import com.example.demo.model.enumclass.MemberStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberApiRequest {

	private Long id;
	
	private String account;
	
	private String password;
	
	private MemberStatus status;
	
	private String email;
	
	private String phoneNumber;
	
	private LocalDateTime registeredAt;
	
	private LocalDateTime unregisteredAt;
	
}
