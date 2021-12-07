package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.network.Header;

@Component
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res>{

	// Entity값을 가지고 JpaRepository를 찾아 Autowired를 시켜준다. 
	@Autowired(required = false)
	protected JpaRepository<Entity, Long> baseRepository;
	// JpaRepository이걸 Autowired받기 위해 Entity를 넘겨주고 있다.(형식 맞추기)
	// JpaRepository<Item, Long>
	
}
