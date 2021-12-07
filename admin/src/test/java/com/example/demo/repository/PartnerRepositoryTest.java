package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AdminApplicationTests;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Partner;
import com.example.demo.model.enumclass.PartnerStatus;

public class PartnerRepositoryTest extends AdminApplicationTests{
	
	@Autowired
	private PartnerRepository partnerRepository;
	
	@Test
	public void create() {
		
		String name = "Test02";
		String status = "REGISTERED";
		String address = "서울시 강남구";
		String callCenter = "010-0000-0000";
		String partnerNumber = "010-1111-2222";
		String businessNumber = "1234567890123";
		String ceoName = "이강남";
		LocalDateTime registeredAt = LocalDateTime.now();
		LocalDateTime createdAt = LocalDateTime.now();
		String createdBy = "AdminServer";
		Category category = new Category();
		category.setId(1L);
		
		Partner partner = new Partner();
		partner.setName(name);
		partner.setStatus(PartnerStatus.REGISTERED);
		partner.setAddress(address);
		partner.setCallCenter(callCenter);
		partner.setPartnerNumber(partnerNumber);
		partner.setBusinessNumber(businessNumber);
		partner.setCeoName(ceoName);
		partner.setRegisteredAt(registeredAt);
		partner.setCreatedAt(createdAt);
		partner.setCreatedBy(createdBy);
		partner.setCategory(category);
		
		Partner newPartner = partnerRepository.save(partner);
		
		assertNotNull(newPartner);
		
	}

}
