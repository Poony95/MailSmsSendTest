package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;

@Controller
@RequestMapping("/deleteBoard")
@Setter
public class DeleteBoardController {
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping(method = RequestMethod.GET)
	public void form(int no, Model model) {		
		model.addAttribute("no", no);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(int no, String pwd, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/listBoard");
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("no", no);
		map.put("pwd", pwd);
		
		//게시물 삭제하기 전에 파일명을 미리 알아 둡니다.
		String fname = dao.findByNo(no).getFname();
		String path = request.getServletContext().getRealPath("upload");
		
		try {
			int re = dao.delete(map);
			if(re == 1) {
				if(fname != null && !fname.equals("")) {
					File file = new File(path + "/" + fname);
					file.delete();
				}
			}
			
		}catch (Exception e) {
			mav.addObject("msg", "게시물 삭제에 실패하였습니다.");
			mav.setViewName("error");
		}
		
		return mav;
	}
}











