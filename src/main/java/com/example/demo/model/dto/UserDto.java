package com.example.demo.model.dto;


import com.example.demo.model.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private Long id;
	private String account;
	private String password;
	
	
	public Member toEntity() {
		return Member.builder().id(id).account(account).password(password).build();
	}
	
	@Builder
	public UserDto(Long id, String account, String password) {
		this.id = id;
		this.account = account;
		this.password = password;
	}
}
