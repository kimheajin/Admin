package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.AdminApplicationTests;
import com.example.demo.model.entity.AdminUser;

public class AdminUserRepositoryTest extends AdminApplicationTests{
	
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Test
	public void create() {
		AdminUser adminUser = new AdminUser();
		
		adminUser.setAccount("AdminUser01");
		adminUser.setPassword("AdminUser01");
		adminUser.setStatus("REGISTERED");
		adminUser.setRole("SUPER");
//		adminUser.setCreatedAt(LocalDateTime.now());
//		adminUser.setCreatedBy("AdminServer");
		
		AdminUser newAdminUser = adminUserRepository.save(adminUser);
		assertNotNull(newAdminUser);
		
		// 위에 데이터를 넣은 후 account를 change한다.
		newAdminUser.setAccount("CHANGE");
		// repository에 변경한 데이터를 넣어준다.
		adminUserRepository.save(newAdminUser);
	}

}
