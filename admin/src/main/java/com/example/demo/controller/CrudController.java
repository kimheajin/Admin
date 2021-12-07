package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.network.Header;
import com.example.demo.service.BaseService;

@Component
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res>{

	// 위에서 넘겨받은 제네릭 Entity는 아래의 baseService로 넘어가게된다.
	@Autowired(required = false)
	protected BaseService<Req, Res, Entity> baseService;
	
	@Override
	@PostMapping("")
	public Header<Res> create(@RequestBody Header<Req> request) {
		return baseService.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<Res> read(@PathVariable Long id) {
		return baseService.read(id);
	}

	@Override
	@PutMapping("")
	public Header<Res> update(@RequestBody Header<Req> request) {
		return baseService.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		return baseService.delete(id);
	}

	
}
