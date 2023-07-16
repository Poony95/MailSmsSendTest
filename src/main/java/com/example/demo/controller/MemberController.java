package com.example.demo.controller;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.MemberDAO;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpSession;
import kr.co.youiwe.webservice.BitSms;
import lombok.Setter;

@Controller
@Setter
public class MemberController {
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private MemberDAO dao;
	
	@GetMapping("/login")
	public void loginForm() {	
	}
	
	@PostMapping("/login")
	public ModelAndView loginSubmit(String id, String pwd, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pwd", pwd);
		boolean re = dao.isMember(map);
		if(re == true) {
			session.setAttribute("id",id);
		}else {
			mav.setViewName("redirect:/login");
		}
		return mav;
	}
	
	@GetMapping("/validCheck")
	@ResponseBody
	public String validCheck(String to, String authType) {
		String data = "";
		Random r = new Random();
		data = r.nextInt(10)+""+r.nextInt(10)+
				""+r.nextInt(10)+""+r.nextInt(10);
		
		if(authType.equals("email")) {
			SimpleMailMessage mailMessage
			= new SimpleMailMessage();
			mailMessage.setFrom("");
			mailMessage.setTo(to);
			mailMessage.setSubject("인증번호를 발송합니다.");
			mailMessage.setText(data);
			try {
				mailSender.send(mailMessage);
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}else {
			BitSms.sendMsg("01072950904", to, data);
		}
				
		return data;
	}
	
	
	@GetMapping("/validPhone")
	@ResponseBody
	public String validPhone(String phone) {
		String data = "";
		Random r= new Random();
		data  = r.nextInt(10) + "" + r.nextInt(10)
		 		+""+r.nextInt(10) +"" + r.nextInt(10);
		BitSms.sendMsg("01072950904", phone, data);
		return data;
	}
	
	
	
	@GetMapping("/validEmail")
	@ResponseBody
	public String validEmail(String email) {
		Random r = new Random();
		int a = r.nextInt(10);
		int b = r.nextInt(10);
		int c = r.nextInt(10);
		int d = r.nextInt(10);
		String data = a+""+b+""+c+""+d;
		SimpleMailMessage mailMessage
		= new SimpleMailMessage();
		mailMessage.setFrom("ryuyein1995@gmail.com");
		mailMessage.setTo(email);
		mailMessage.setSubject("인증번호를 발송합니다.");
		mailMessage.setText(data);
		try {
			mailSender.send(mailMessage);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		
		return data;
	}
	
	@GetMapping("/join")
	public void joinForm() {		
	}
	
	@PostMapping("/join")
	public ModelAndView joinSubmit(MemberVO m) {		
		ModelAndView mav = new ModelAndView("redirect:/login");
		try {
			int re = dao.insert(m);
			if(re != 1) {
				mav.addObject("msg", "회원가입에 실패하였습니다.");
				mav.setViewName("error");
			}
			
		}catch (Exception e) {
			mav.addObject("msg", "회원가입에 실패하였습니다.");
			mav.setViewName("error");
		}
		
		return mav;
	}
	
}









