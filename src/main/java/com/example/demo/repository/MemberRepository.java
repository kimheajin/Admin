package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Member findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
	
	Optional<Member> findByaccount(String account);
	
}

