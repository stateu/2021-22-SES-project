package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ScheduleService;
import com.example.service.ScheduleServiceImp;
import com.example.service.UserServiceImpl;
import com.example.vo.ScheduleVO;
import com.example.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ScheduleManagement")
public class ScheduleController {

	@Autowired
	ScheduleServiceImp scheduleService;
	
	@Autowired
	UserServiceImpl userService;
	
	// ScheduleManagement basic index
	@RequestMapping("/")
	public String index(Model model, @AuthenticationPrincipal UserDetails user) {

		String targetId = user.getUsername();
		log.info("현재 로그인된 user ID : " + targetId);
		
		// weekly schedule list
		List<ScheduleVO> weeklySchedule = scheduleService.getWeeklySchedule(targetId);
		
		model.addAttribute("weeklyPlan", weeklySchedule);
		
		// important schedule list
		// 현재 날짜 이후의 중요표시 일정들
		List<ScheduleVO> importantSchedule = scheduleService.getImportantSchedule(targetId);
		
		model.addAttribute("importantPlan", importantSchedule);
		
		// 생일 칸
		UserVO vo = userService.findById(targetId);
		String targetBirth = vo.getBirth();
		log.info(vo.toString());
		
		targetBirth = targetBirth.replaceAll("-", "");

		ScheduleVO birth = scheduleService.searchBirth(targetId, targetBirth);
		
		model.addAttribute("birthDay", birth);
		
		log.info("확인용: " + birth);
		
		return "ScheduleManagement/index"; // board폴더 밑의 index파일
	}
	
	// move to add Schedule
	@RequestMapping("/addScheduleForm")
	public String addScheduleForm() {
		return "ScheduleManagement/newSchedule";
	}
	
	// click button add Schedule
	@PostMapping("/addSchedule")
	public String addSchedule(ScheduleVO vo, @AuthenticationPrincipal UserDetails user) {
		String id = user.getUsername(); // 현재 로그인중인 사용자의 id
		vo.setUserId(id);
		
		scheduleService.addSchedule(vo);
		
		System.out.println(vo);
		
		log.info(user.getUsername() + ": 일정 추가 완료");
		return "redirect:/ScheduleManagement/";
	}
	
	// add birthday schedule
	@PostMapping("/addBirthday")
	public void addBirthday(String id, String birthday) {
		
		String userId = id;
		String userBirth = birthday;
		
		ScheduleVO birth = new ScheduleVO();
		
		birth.setUserId(userId);
		birth.setStartDate(userBirth);
		birth.setEndDate(userBirth);
		birth.setImportance(1);
		birth.setTitle("Birthday");
		birth.setContent("");
		birth.setPlace("");
		
		scheduleService.addSchedule(birth);
		
		log.info(userId + "의 생일을 " + userBirth + "로 입력완료");
	}
	
	// view detail of Schedule
	@GetMapping("/viewOneSchedule")
	public String viewOneSchedule(int idx_num, Model model) {
		log.info("현재 index number: " + idx_num);
		
		ScheduleVO oneSchedule = scheduleService.getOneSchedule(idx_num);
		
		model.addAttribute("onePlan", oneSchedule);
		
		return "ScheduleManagement/view";
	}
	
	// move to modify Schedule
	@RequestMapping("/modifyScheduleForm")
	public String modifyScheduleForm(int idx_num, Model model, @AuthenticationPrincipal UserDetails user) {
		// 수정할 일정 선택
		ScheduleVO targetSchedule = scheduleService.getOneSchedule(idx_num);
		targetSchedule.setUserId(user.getUsername());
		
		log.info("타켓 : " + targetSchedule);
		// 페이지 이동 시 함께 넘어가도록 설계
		model.addAttribute("targetPlan", targetSchedule);
		
		log.info("넘어가는 index number: " + targetSchedule.getIdx_num());
		return "ScheduleManagement/updateSchedule";
	}
	
	// click button update Schedule
	@PostMapping("/modifySchedule")
	public String updateArticle(ScheduleVO vo) {
		
		log.info("수정 완료된 index number: " + vo.toString());
		
		scheduleService.modifySchedule(vo);
		
		return "redirect:/ScheduleManagement/";
	}
	
	// click button delete Schedule
	@GetMapping("/deleteSchedule")
	public String deleteSchedule(int idx_num) {
		scheduleService.deleteSchedule(idx_num);
		return "redirect:/ScheduleManagement/";
	}
	
	// selected date schedule
	@PostMapping("/searchSchedule")
	public String searchSchedule(String selectedDate, Model model, @AuthenticationPrincipal UserDetails user) {
		
		String targetId = user.getUsername();
		
		List<ScheduleVO> resultSchedule = scheduleService.getNowSchedule(selectedDate, targetId);
		
		log.info(selectedDate + "검색");
		
		model.addAttribute("selectedDateSchedule", resultSchedule);

		return"ScheduleManagement/index :: #sele";
	}
}
