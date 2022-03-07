package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ScheduleDAO;
import com.example.vo.ScheduleVO;

@Service
public class ScheduleServiceImp implements ScheduleService{
	
	@Autowired
	ScheduleDAO dao;

	@Override
	public List<ScheduleVO> getWeeklySchedule(String targetId) {
		List<ScheduleVO> weeklySchedule = dao.weeklySchedule(targetId);
		return weeklySchedule;
	}

	@Override
	public ScheduleVO getOneSchedule(int idx_num) {
		ScheduleVO vo = dao.detailOne(idx_num);
		return vo;
	}

	@Override
	public List<ScheduleVO> getImportantSchedule(String targetId) {
		List<ScheduleVO> importantSchedule = dao.importantSchedule(targetId);
		return importantSchedule;
	}

	@Override
	public int addSchedule(ScheduleVO vo) {
		return dao.insertSchedule(vo);
	}

	@Override
	public int modifySchedule(ScheduleVO vo) {
		return dao.updateSchedule(vo);
	}

	@Override
	public int deleteSchedule(int idx_num) {
		return dao.deleteSchedule(idx_num);
	}

	@Override
	public List<ScheduleVO> getNowSchedule(String selectedDate, String targetId) {
		List<ScheduleVO> selectedDateSchedule = dao.serachByDate(selectedDate, targetId);
		return selectedDateSchedule;
	}

	@Override
	public ScheduleVO searchBirth(String targetId, String birthday) {
		ScheduleVO vo = dao.searchBirth(targetId, birthday);
		return vo;
	}

}
