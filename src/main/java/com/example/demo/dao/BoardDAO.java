package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.BoardVO;

@Repository
public class BoardDAO {
	
	//한 화면에 보여줄 레코드의 수
	public static int pageSIZE=10;
	
	//전체레코드의 수
	public static int totalRecord;
	
	//전체페이지의 수
	public static int totalPage;
	
	public int update(BoardVO b) {
		return DBManager.update(b);
	}
	
	public void updateStep(HashMap<String, Object> map) {
		DBManager.updateStep(map);
	}
	
	public void updateHit(int no) {
		DBManager.updateHit(no);
	}
	public BoardVO findByNo(int no) {
		return DBManager.findByNo(no);
	}
	
	public int insert(BoardVO b) {
		return DBManager.insert(b);
	}
	public List<BoardVO> findAll(HashMap<String, Object> map){
		totalRecord = DBManager.getTotalRecord();
		totalPage=(int)Math.ceil(totalRecord/(double)pageSIZE); 
		
		return DBManager.findAll(map);
	}
	public int getNextNo() {
		return DBManager.getNextNo();
	}

	public int delete(HashMap<String, Object> map) {		
		return DBManager.delete(map);
	}
}












