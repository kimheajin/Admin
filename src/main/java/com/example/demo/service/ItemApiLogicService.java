package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.Item;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.ItemApiRequest;
import com.example.demo.model.network.response.ItemApiResponse;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PartnerRepository;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

	@Autowired
	private PartnerRepository partnerRepository;
	
	@Override
	public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
		ItemApiRequest body = request.getData();
		
		Item item = Item.builder()
				.status(body.getStatus())
				.name(body.getName())
				.title(body.getTitle())
				.content(body.getContent())
				.price(body.getPrice())
				.brandName(body.getBrandName())
				.registeredAt(LocalDateTime.now())
				.partner(partnerRepository.getById(body.getPartnerId()))
				.build();
		
		Item newItem = baseRepository.save(item);
		return response(newItem);
	}

	@Override
	public Header<ItemApiResponse> read(Long id) {
		
		return baseRepository.findById(id)
						.map(item -> response(item))
						.orElseGet(()->{
							return Header.ERROR("데이터 없음");
						});
	}

	@Override
	public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
		
		ItemApiRequest body = request.getData();
		
		return baseRepository.findById(body.getId())
						.map(entityItem -> {
							entityItem.setStatus(body.getStatus())
										.setName(body.getName())
										.setTitle(body.getTitle())
										.setContent(body.getContent())
										.setPrice(body.getPrice())
										.setBrandName(body.getBrandName())
										.setRegisteredAt(body.getRegisteredAt())
										.setUnregisteredAt(body.getUnregisteredAt());
							
							return entityItem; 
						})
						.map(newEntityItem->baseRepository.save(newEntityItem))
						// .map(newEntityItem->{itemRepository.save(newEntityItem) return newEntityItem;})
						.map(item->response(item))
						.orElseGet(()->Header.ERROR("데이터 없음"));
		
	}

	@Override
	public Header delete(Long id) {
		
		return baseRepository.findById(id)
					.map(item -> {
						baseRepository.delete(item);
						return Header.OK();
					})
					.orElseGet(()->Header.ERROR("데이터 없음"));
	}
	
	public Header<ItemApiResponse> response(Item item){
		ItemApiResponse body = ItemApiResponse.builder()
				.id(item.getId())
				.status(item.getStatus())
				.name(item.getName())
				.title(item.getTitle())
				.content(item.getContent())
				.price(item.getPrice())
				.brandName(item.getBrandName())
				.registeredAt(item.getRegisteredAt())
				.unregisteredAt(item.getUnregisteredAt())
				.partnerId(item.getPartner().getId())
				.build();
		
		return Header.OK(body);
	}

}
