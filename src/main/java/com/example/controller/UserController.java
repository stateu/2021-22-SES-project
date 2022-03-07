package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import com.example.service.UserServiceImpl;
import com.example.vo.UserVO;
@Controller
@Slf4j
public class UserController{
	//로그인 기능. 알아서 실행됨.(config), 체크 완료 config 파일에서 어디로 연결되는지 지정만 하면 됨.
	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private ScheduleController scheduleController;
	
	@PostMapping("/login")
	public String login(String userId, String userPw) {
			return ""; 
		}
	//로그인 화면에서 회원가입 페이지로 이동, 체크 완료
	@RequestMapping("/showJoinSchedule")
	public String showJoinSchedule() {
		return "user/joinSchedule";
	}
	
	//첫 화면에서 로그인화면으로 이동, 체크 완료
	
	@RequestMapping("/showLoginSchedule") public String showLoginSchedule() {
		return "user/loginSchedule";
	}
	

	
	//회원가입 페이지에서 회원가입 버튼 눌렀을 때 객체 정보를 DB삽입하고 다시 로그인 화면으로 이동, 체크 완료
	@PostMapping("/join")
	public String join(UserVO vo) {
		String id = vo.getUserId();
		String birthday = vo.getBirth();
		
		service.insertUser(vo);
		
		scheduleController.addBirthday(id, birthday);
		
		return "user/loginSchedule"; // 로그인 화면 표시
	}
			
	/*
	 * //회원 정보수정
	 * 
	 * @RequestMapping("/showInfoUpdateForm") public String
	 * showInfoUpdateForm(@AuthenticationPrincipal UserDetails user, Model model) {
	 * return "user/updateForm";
	 }
	
	//수정 완료시 다시 로그인 화면으로 
	@PostMapping("/updateUser")
	public String updateUser(UserVO vo) {
		
		service.updateUser(vo);
		return "loginSchedule";
	}*/
	
	//id찾기 페이지로 이동
		@RequestMapping("/showFindId")
		public String showFindId() {
			return "user/findId";
			}
			
		//id찾기
		@PostMapping("/findId")
		public String findId(UserVO vo, Model model) {
			UserVO IDvo=service.getUserId(vo);
			log.info(IDvo.toString());
			model.addAttribute("user",IDvo);
			return "user/showId";
		}
		//pw찾기 페이지로 이동
		@RequestMapping("/showFindPw")
		public String showFindPw() {
			return "user/findPw";
			
		}
		//pw찾기
		@RequestMapping("/findPw")
		public String findPw(UserVO vo) {
			service.resetUserPw(vo);
			return "user/loginSchedule";
		}
	
}	

