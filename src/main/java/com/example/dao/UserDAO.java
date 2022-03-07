package com.example.dao;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.UserVO;
@Mapper
public interface UserDAO {	
	
	//회원가입(DB에 userVO 삽입)
	public int insertUser(UserVO vo);
		
	//로그인 및 id체크
	public UserVO selectUser(UserVO vo);
	//회원 정보 수정
	public int updateUser(UserVO vo);
		
	//userId에 대한 UserVO 정보 확인
	public UserVO findById(String userId);
	
	//전화번호와 생년월일을 통해서 id 찾기 
	public UserVO getuserId(UserVO vo); 
	
	//id 전화번호, 생년월일로pw 찾기	 
	public int resetuserPw(UserVO vo);
	 
	
	
}