package com.example.demo.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartnerStatus {

	REGISTERED(0, "등록", "파트너 등록 상태"),
	UNREGISTERED(1, "해지", "파트너 해지 상태"),
	WAITING(2, "대기", "파트너 수락 대기 상태");
	
	private Integer id;
	private String title;
	private String description;
	
}
