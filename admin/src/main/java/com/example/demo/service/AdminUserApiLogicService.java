package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.AdminUser;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.AdminUserApiRequest;
import com.example.demo.model.network.response.AdminUserApiResponse;
import com.example.demo.repository.AdminUserRepository;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser>{

	@Override
	public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
		AdminUserApiRequest body =  request.getData();
		
		AdminUser adminUser = AdminUser.builder()
										.account(body.getAccount())
										.password(body.getPassword())
										.status(body.getStatus())
										.role(body.getRole())
										.lastLoginAt(body.getLastLoginAt())
										.loginFailCount(body.getLoginFailCount())
										.passwordUpdatedAt(body.getPasswordUpdatedAt())
										.registeredAt(LocalDateTime.now())
										.unregisteredAt(body.getUnregisteredAt())
										.build();
		
		AdminUser newAdminUser = baseRepository.save(adminUser);
		return response(newAdminUser);
	}

	@Override
	public Header<AdminUserApiResponse> read(Long id) {
		return baseRepository.findById(id)
							.map(adminUser->baseRepository.save(adminUser))
							.map(newAdminUser->response(newAdminUser))
							.orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
		AdminUserApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
							.map(EntityAdminUser -> {
								EntityAdminUser.setAccount(body.getAccount())
										 .setPassword(body.getPassword())
										 .setStatus(body.getStatus())
										 .setRole(body.getRole())
										 .setLastLoginAt(body.getLastLoginAt())
										 .setLoginFailCount(body.getLoginFailCount())
										 .setPasswordUpdatedAt(body.getPasswordUpdatedAt())
										 .setRegisteredAt(body.getRegisteredAt())
										 .setUnregisteredAt(body.getUnregisteredAt());
								
								return EntityAdminUser;
							})
							.map(newAdminUser->baseRepository.save(newAdminUser))
							.map(adminUser->response(adminUser))
							.orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
								  .map(adminUser -> {
									  baseRepository.delete(adminUser);
									  return Header.OK();
								  })
								  .orElseGet(()->Header.ERROR("데이터 없음"));
	}
	
	private Header<AdminUserApiResponse> response(AdminUser adminUser){
		
		AdminUserApiResponse body = AdminUserApiResponse.builder()
														.account(adminUser.getAccount())
														.password(adminUser.getPassword())
														.status(adminUser.getStatus())
														.role(adminUser.getRole())
														.lastLoginAt(adminUser.getLastLoginAt())
														.loginFailCount(adminUser.getLoginFailCount())
														.passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
														.registeredAt(adminUser.getRegisteredAt())
														.unregisteredAt(adminUser.getUnregisteredAt())
														.build();
		
		return Header.OK(body);
	}

}
