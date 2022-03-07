package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.UserDAO;
import com.example.vo.UserVO;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int insertUser(UserVO vo) {
		//vo 내의 비밀번호를 암호화
		String encodedPassword=passwordEncoder.encode(vo.getUserPw());
		vo.setUserPw(encodedPassword);
		return dao.insertUser(vo);
	}

	@Override
	public UserVO selectUser(UserVO vo) {
		//암호화된 vo 객체 비밀번호 반환
		String encodedPassword=passwordEncoder.encode(vo.getUserPw());
		UserVO result=dao.selectUser(vo);
		return result;
	}


	@Override
	public UserVO findById(String userId) {
		return dao.findById(userId);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO result=dao.findById(username);
		
		if(result==null) {
			throw new UsernameNotFoundException(username);
		}
		return result;
	}

	@Override
	public int updateUser(UserVO vo) {
		String encodedPassword=passwordEncoder.encode(vo.getUserPw());
		vo.setUserPw(encodedPassword);
		return dao.updateUser(vo);
	}

	@Override
	public UserVO getUserId(UserVO vo) {
		// 
		return dao.getuserId(vo);
	}

	
		

	@Override
	public int resetUserPw(UserVO vo) {
		String encodedPassword=passwordEncoder.encode(vo.getUserPw());
		vo.setUserPw(encodedPassword);
		return dao.resetuserPw(vo); 
	}

}

