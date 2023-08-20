package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.AdminApplicationTests;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.OrderDetail;
import com.example.demo.model.entity.Member;
import com.example.demo.model.enumclass.MemberStatus;

public class UserRepositoryTest extends AdminApplicationTests{
	
	@Autowired  
	private MemberRepository userRepository;
	
	@Test
	public void create() {
		String account = "Test03";
		String password = "Test03";
		MemberStatus status = MemberStatus.REGISTERED;
		String email = "Test03@gmail.com";
		String phoneNumber = "010-0000-3333";
		LocalDateTime registeredAt = LocalDateTime.now();
//		LocalDateTime createdAt = LocalDateTime.now();
//		String createdBy = "AdminServer";
		
		Member user = new Member();
		
		user.setAccount(account);
		user.setPassword(password);
		user.setStatus(status);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setRegisteredAt(registeredAt);
		
		// builder를 이용하면 재정의하려는 클래스에 생성자를 추가하지 않고 점 연산자를 이용해 생성자를 생성할 수 있다.
		Member u = Member.builder()
				.account(account)
				.password(password)
				.status(status)
				.email(email)
				.build();
		
		Member newUser = userRepository.save(user);
		
		assertNotNull(newUser);
	}
	
	@Test
	@Transactional
	public void read() {
		// optional은 말그대로 옵션을 사용하기 위한 것.
		Member user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-0000-1111");
		
		// Accessors(chain=true)를 이용하면 아래와 같이 여러개의 set을 한줄에 넣을 수 있다.
		user.setAccount(null).setEmail(null).setPhoneNumber(null);
		// 이것은 굳이 생성자를 만들지 않아도 3개의 값을 가지는 객체를 만들어낼 수도 있다. 
		Member u = new Member().setAccount("").setEmail("").setPassword("");
		
		
		user.getOrderGroupList().stream().forEach(orderGroup -> {
			System.out.println("--------주문묶음---------");
			System.out.println("수령인 : "+orderGroup.getRevName());
			System.out.println("수령지 : "+orderGroup.getRevAddress());
			System.out.println("총금액 : "+orderGroup.getTotalPrice());
			System.out.println("총수량 : "+orderGroup.getTotalQuantity());
			
			System.out.println("--------주문상세---------");
			orderGroup.getOrderDetailList().forEach(orderDetail->{
				System.out.println("파트너사 이름 : "+orderDetail.getItem().getPartner().getName());
				System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
				
				System.out.println("주문 상품 : "+orderDetail.getItem().getName());
				System.out.println("고객센터 번호 : "+orderDetail.getItem().getPartner().getCallCenter());
				System.out.println("주문 상태 : "+orderDetail.getStatus());
				
				
			});
		});
		
		assertNotNull(user);
		
		// user에 값이 있다면 selectUser에 그 값을 넣어, 안의 내용을 실행.
//		user.ifPresent(selectUser ->{
//			selectUser.getOrderDetailList().stream().forEach(detail->{
//				Item item = detail.getItem();
//				System.out.println(item);
//			});
			
//			System.out.println("user: " + selectUser);
//			System.out.println("email: " + selectUser.getEmail());
//		});
	}
	
	@Test
	public void update() {
		Optional<Member> user = userRepository.findById(2L);
		
		// 위의 findById에서 이미 id값을 찾았기 때문에 아래는 해당 id값을 기준으로 변경을 해준다.
		user.ifPresent(selectUser ->{
			selectUser.setAccount("PPPP");
			selectUser.setUpdatedAt(LocalDateTime.now());
			selectUser.setUpdatedBy("update method()");
			
			userRepository.save(selectUser);
		});
	}

	@Test
	// @Transactional //이 어노테이션은 아래의 SQL기능이 실행되어도 다시 롤백시켜주는 역할을 한다.
	public void delete() {
		Optional<Member> user = userRepository.findById(3L);
		
		// assertTrue는 값이 True인지 False인지를 확인해주는 메소드
		assertTrue(user.isPresent());
		
		// user가 값이 있다면 아래의 작업을 통해 지운다.
		user.ifPresent(selectUser->{
			userRepository.delete(selectUser);
		});
		
		// 다시 한 번 user가 값이 있는지 확인하기 위한 코드
		Optional<Member> deleteUser = userRepository.findById(3L);
		
		// 만약 deleteUser의 값이 있다면 코드 실행
		if(deleteUser.isPresent()) {
			System.out.println("데이터 존재 : " + deleteUser);
		}else {
			// 만약 deleteUser의 값이 존재하지 않을 경우 아래 코드 실행.
			System.out.println("데이터 삭제 데이터 없음");
		}
		
		
	}
}
