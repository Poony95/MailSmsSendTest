package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

@Controller
@Setter
public class MailController {

	@Autowired
	private MailSender mailSender;
	
	// 4자리 난수를 발행하여 메일로 전송 해 봅니다.
	@GetMapping("/sendMail")
	@ResponseBody
	public String mail() {
		SimpleMailMessage mailMessage = 
				new SimpleMailMessage();
		
		Random r = new Random();
		int a = r.nextInt(10); //0~9
		int b = r.nextInt(10); //0~9
		int c = r.nextInt(10); //0~9
		int d = r.nextInt(10); //0~9
		
		String data = a+""+b+""+c+""+d;
		
		mailMessage.setFrom("ryuyein1995@gmail.com");
		mailMessage.setTo("654862@naver.com");
		mailMessage.setSubject("예인아, 안녕~");
//		mailMessage.setText("메일 보내기 연습하고 있어,,");
		mailMessage.setText(data);
		
		try {
			mailSender.send(mailMessage);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return "OK";
	}
	
}










