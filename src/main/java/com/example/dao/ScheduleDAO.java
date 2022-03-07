package com.example.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.ScheduleVO;

@Mapper
public interface ScheduleDAO {

	// 일정 등록
	public int insertSchedule(ScheduleVO vo);
	
	// 일정 수정
	public int updateSchedule(ScheduleVO vo);
	
	// 일정 삭제 (schedule 번호를 이용)
	public int deleteSchedule(int idx_num);
	
	// 일정 확인 (index number를 통한 비교) (== 일정 상세보기)
	public ScheduleVO detailOne(int idx_num);
	
	// 중요 표시 글 (importance == 1)
	public List<ScheduleVO> importantSchedule(String targetId);
	
	// 전체 일정 띄우기
	public List<ScheduleVO> weeklySchedule(String targetId);
	
	// 생일 띄우기
	public ScheduleVO searchBirth(String targetId, String targetBirth);
	
	// 일정 검색
	// 날짜로
	public List<ScheduleVO> serachByDate(String selectedDate, String targetId);
	
	// 제목으로
	public List<ScheduleVO> searchByTitle();
	
	// 장소로
	public List<ScheduleVO> searchByPlace();
}
