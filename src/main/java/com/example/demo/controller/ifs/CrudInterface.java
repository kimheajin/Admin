package com.example.demo.controller.ifs;

import com.example.demo.model.network.Header;

public interface CrudInterface<Req, Res> {
	
	Header<Res> create(Header<Req> request); // request object추가
	
	Header<Res> read(Long id);
	
	Header<Res> update(Header<Req> request);
	
	Header delete(Long id);
	
}
