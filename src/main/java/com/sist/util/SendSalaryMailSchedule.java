package com.sist.util;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.EmpDAO;
import com.example.demo.vo.EmpVO;
import com.sist.util.MyUtil;

import lombok.Setter;

@Component
@EnableScheduling
@Setter
public class SendSalaryMailSchedule {
	@Autowired
	private EmpDAO dao;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender sender;
	
	
					 //초 분 시간 일 월 요일 연도
//	@Scheduled(cron = "20 46 14 5 * ?")
	public void sendHtml() {
		List<EmpVO> list = dao.findAll();
		for(EmpVO e:list) {
			String from = "";
			String to = e.getEmail();
			String data = MyUtil.getHtml(e);
			sender.send(new MimeMessagePreparator() {
				
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					// TODO Auto-generated method stub
					MimeMessageHelper message = 
							new MimeMessageHelper(mimeMessage, "UTF-8");
					message.setFrom(from);
					message.setTo(to);
					message.setSubject("급여명세서[담당자:김은영]");
					message.setText(data, true);
				}
			});
			
			System.out.println(e.getEname()+"("+ to +")님에게 급여명세서를 발송하였습니다.");
		}
	}
		
		//초 분 시간 일 월 요일 연도
//		@Scheduled(cron = "50 19 12 5 * ?")
		public void send() {
			List<EmpVO> list = dao.findAll();
			for(EmpVO e:list) {
				String to = e.getEmail();
				int total = e.getSalary()+e.getComm();
				String title = e.getEname()+"님, 이번달 급여명세서입니다. [담당자 : 김은영]";
				String data = "이번달 급여는 "+ total+"만원입니다.";
				
				SimpleMailMessage mailMessage = 
						new SimpleMailMessage();
				mailMessage.setFrom("rolakim70@gmail.com");
				mailMessage.setTo(to);
				mailMessage.setSubject(title);
				mailMessage.setText(data);
				
				mailSender.send(mailMessage);
				
			}
	}
}













