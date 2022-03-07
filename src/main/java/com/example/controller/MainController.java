package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

//로그인 화면
@Controller
@Slf4j
public class MainController {

	
	@RequestMapping("/")
	public String firstweb() {
		
		return "firstweb"; //첫 화면 띄우기. 체크 완료
	}
	
}
