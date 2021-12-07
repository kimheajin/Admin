package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.Partner;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.PartnerApiRequest;
import com.example.demo.model.network.response.PartnerApiResponse;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PartnerRepository;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner>{

	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Override
	public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
		PartnerApiRequest body = request.getData();
		
		Partner partner = Partner.builder()
								.name(body.getName())
								.status(body.getStatus())
								.address(body.getAddress())
								.partnerNumber(body.getPartnerNumber())
								.businessNumber(body.getBusinessNumber())
								.ceoName(body.getCeoName())
								.registeredAt(body.getRegisteredAt())
								.unregisteredAt(body.getUnregisteredAt())
								.callCenter(body.getCallCenter())
								.category(categoryRepository.getById(body.getCategoryId()))
								.build();
								
		Partner newPartner = baseRepository.save(partner);
		return response(newPartner);
	}

	@Override
	public Header<PartnerApiResponse> read(Long id) {
		return baseRepository.findById(id)
						 .map(partner->response(partner))
						 .orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
		PartnerApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
						 .map(entityPartner->{
							 entityPartner.setName(body.getName())
							 			  .setStatus(body.getStatus())
							 			  .setAddress(body.getAddress())
							 			  .setPartnerNumber(body.getPartnerNumber())
							 			  .setBusinessNumber(body.getBusinessNumber())
							 			  .setCeoName(body.getCeoName())
							 			  .setRegisteredAt(body.getRegisteredAt())
							 			  .setUnregisteredAt(body.getUnregisteredAt())
							 			  .setCallCenter(body.getCallCenter())
							 			  .setCategory(categoryRepository.getById(body.getCategoryId()));
							 
							 return entityPartner;
						 })
						 .map(newPartner->baseRepository.save(newPartner))
						 .map(partner->response(partner))
						 .orElseGet(()->Header.ERROR("데이터 없음"));
	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id)
				.map(partner->{
					baseRepository.delete(partner);
					return Header.OK();
				})
				.orElseGet(()->Header.ERROR("데이터 없음"));
	}
	
	private Header<PartnerApiResponse> response(Partner partner){
		PartnerApiResponse body = PartnerApiResponse.builder()
													.name(partner.getName())
													.status(partner.getStatus())
													.address(partner.getAddress())
													.callCenter(partner.getCallCenter())
													.partnerNumber(partner.getPartnerNumber())
													.businessNumber(partner.getBusinessNumber())
													.ceoName(partner.getCeoName())
													.registeredAt(partner.getRegisteredAt())
													.unregisteredAt(partner.getUnregisteredAt())
													.categoryId(partner.getCategory().getId())
													.build();
		
		return Header.OK(body);
	}

}
