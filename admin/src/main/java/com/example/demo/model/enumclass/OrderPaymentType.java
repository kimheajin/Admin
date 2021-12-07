package com.example.demo.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderPaymentType {
	
	LUMPSUM(0, "일시불", "일시불로 금액을 지불"),
	MONTHLYPAYMENT(1, "할부", "금액을 할부로 지불");
	
	private Integer id;
	private String title;
	private String description;
}
