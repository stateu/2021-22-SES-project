package com.example.service;

import com.example.vo.UserVO;
public interface UserService {
	
	public int insertUser(UserVO vo);
	
	public UserVO selectUser(UserVO vo);
	
	public int updateUser(UserVO vo);
	
	public UserVO findById(String userId);
	
	
	public UserVO getUserId(UserVO vo);
	
	public int resetUserPw(UserVO vo);	
}
