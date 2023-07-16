package com.example.demo.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

@Controller
@Setter
public class HtmlMailController {
	
	@Autowired
	private JavaMailSender sender;
	
	@GetMapping("/sendHtmlEmail")
	@ResponseBody
	public String send() {
		
		sender.send(new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				MimeMessageHelper message =
					new MimeMessageHelper(mimeMessage, "utf-8");
				message.setFrom("ryuyein1995@gmail.com");
				message.setTo("654862@naver.com");
				message.setSubject("html형태의 이메일전송");
				message.setText("<h2>안녕,,</h2>", true);				
			}
		});
		
		return "OK";
	}
}






