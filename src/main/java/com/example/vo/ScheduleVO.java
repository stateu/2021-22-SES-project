package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleVO {
	
	private int idx_num; // index number
	private String userId; // userid
	private String title; // title
	private String content; // content
	private String place; // place
	private String startDate; // start date
	private String endDate; // end date
	private int importance; // importance 0: not, 1: important
}
