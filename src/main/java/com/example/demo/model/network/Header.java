package com.example.demo.model.network;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

	// api 통신 시간
	// @JsonProperty("transaction_time") 이런식으로 JSON에 출력될 형태를 지정할 수 있다.
	private LocalDateTime transactionTime;
	
	// api 응답 코드
	private String resultCode;
	
	// api 부가 설명
	private String description;

	private T data;
	
	private Pagination pagenation;
	
	
	// OK
	public static <T> Header<T> OK(){
		return (Header<T>)Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.build();
	}
	
	// DATA OK
	public static <T> Header<T> OK(T data){
		return (Header<T>)Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.data(data)
				.build();
	}
	
	// DATA OK
		public static <T> Header<T> OK(T data, Pagination pagination){
			return (Header<T>)Header.builder()
					.transactionTime(LocalDateTime.now())
					.resultCode("OK")
					.description("OK")
					.data(data)
					.pagenation(pagination)
					.build();
		}
	
	// ERROR
	public static <T> Header<T> ERROR(String description){
		return (Header<T>)Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("ERROR")
				.description(description)
				.build();
	}
	
	
	
}
