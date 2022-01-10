package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AdminApplicationTests;
import com.example.demo.model.entity.Item;
import com.example.demo.model.enumclass.ItemStatus;

public class ItemRepositoryTest extends AdminApplicationTests{
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void create() {
		
		Item item = new Item();
		item.setStatus(ItemStatus.UNREGISTERED);
		item.setName("삼성컴퓨터");
		item.setTitle("삼성 컴퓨터 F001");
		item.setContent("2021년형 노트북");
		item.setPrice(BigDecimal.valueOf(500000));
		item.setBrandName("삼성");
		item.setRegisteredAt(LocalDateTime.now());
		item.setCreatedAt(LocalDateTime.now());
		item.setCreatedBy("Partner01");
//		item.setPartner(1L);
		
		Item newItem = itemRepository.save(item);
		assertNotNull(newItem);
	}
	
	@Test
	public void read() {
		Long id = 1L;
		Optional<Item> item = itemRepository.findById(id);
		
		// 이거로 정상적인 값이 들어왔는지 확인가능.
		assertTrue(item.isPresent());
		
//		item.ifPresent(i -> {
//			System.out.println(i);
//		});
		
	}
	
	@Test
	public void update() {}
	
	@Test
	public void delete() {}
}
