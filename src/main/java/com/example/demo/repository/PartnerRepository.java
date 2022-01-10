package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long>{

	List<Partner> findByCategory(Category category);

}
