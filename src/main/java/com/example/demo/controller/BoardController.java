package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/detailBoard")
	public void detail(Model model, int no) {
		dao.updateHit(no);
		model.addAttribute("b", dao.findByNo(no));
	}
	
	@RequestMapping("/listBoard")
	public void list(Model model,
			@RequestParam(value = "pageNUM", 
			defaultValue = "1")  int pageNUM) {
		
		System.out.println("pageNUM:"+pageNUM);
		int start = (pageNUM-1)* BoardDAO.pageSIZE +1;
		int end = start +  BoardDAO.pageSIZE -1;
		// findAll을 호출해야지만 
		// totalRecorld계산이 됩니다.
		// 계산되기 전의 기본값이 0이라서 바람직한 end가 구해지지 
		// 않아요!
//		if(end > BoardDAO.totalRecord) {
//			end = BoardDAO.totalRecord;
//		}
		
		HashMap<String, Object> map 
		= new HashMap<String,Object>();
		map.put("start", start);
		map.put("end", end);
		
		model.addAttribute("list", dao.findAll(map));
		model.addAttribute("totalPage", dao.totalPage);
		
		//나머지 코드를 완성하여 결과를 확인합니다.
		
	}
}












