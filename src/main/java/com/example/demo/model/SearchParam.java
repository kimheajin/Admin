package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 모든 매개변수를 받는 생성자를 추가하는 어노테이션.
public class SearchParam {
	private String account;
	private String email;
	private int page;
	
}
