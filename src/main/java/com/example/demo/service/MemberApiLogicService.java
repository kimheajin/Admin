package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Member;
import com.example.demo.model.enumclass.Role;
import com.example.demo.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@AllArgsConstructor
public class MemberApiLogicService implements UserDetailsService{
	
	private MemberRepository memberRepository;
	
	// 회원가입
	@Transactional
	public Long signUp(UserDto userDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		return memberRepository.save(userDto.toEntity()).getId();
	}
	
	public UserDetails loadUserByAccount(String account) throws AccountNotFoundException{
		Optional<Member> userWrapper = memberRepository.findByaccount(account);
		Member user = userWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if("admin".equals(account)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		return new User(user.getAccount(), user.getPassword(), authorities);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
