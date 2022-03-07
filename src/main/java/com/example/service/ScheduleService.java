package com.example.service;

import java.util.List;

import com.example.vo.ScheduleVO;

public interface ScheduleService {
	
	// 메인화면 일정 띄우기
	public List<ScheduleVO> getWeeklySchedule(String targetId);
	
	// 일정 상세보기
	public ScheduleVO getOneSchedule(int idx_num);
	
	// 중요글 보기
	public List<ScheduleVO> getImportantSchedule(String targetId);
	
	// 일정 등록
	public int addSchedule(ScheduleVO vo);
	
	// 일정 수정
	public int modifySchedule(ScheduleVO vo);
	
	// 일정 삭제
	public int deleteSchedule(int idx_num);
	
	// 해당 날짜 검색
	public List<ScheduleVO> getNowSchedule(String selectedDate, String targetId);
	
	// 생일 찾기
	public ScheduleVO searchBirth(String targetId, String birthday);
	
}
