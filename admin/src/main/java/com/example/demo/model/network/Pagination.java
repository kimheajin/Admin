package com.example.demo.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {

	// 총 몇 페이지
	private Integer totalPages;
	// 총 몇개의 엘리먼트? 총 몇개의 유저를 보유하고있나.
	private Long totalElements;
	// 현재 페이지 위치
	private Integer currentPage;
	// 현재 몇개의 데이터가 있는지
	private Integer currentElements;
}
