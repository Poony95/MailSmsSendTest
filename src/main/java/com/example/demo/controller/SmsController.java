package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.youiwe.webservice.BitSms;

@Controller
public class SmsController {

	@GetMapping("/sendMessage")
	@ResponseBody
	public String sendMessage() {
		String from = "01072950904";
		String to  = "01072950904";
		String msg = "문자연습,,";
		BitSms.sendMsg(from, to, msg);
		return "OK";
	}
}
