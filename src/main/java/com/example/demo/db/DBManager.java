package com.example.demo.db;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.javassist.bytecode.annotation.MemberValue;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.EmpVO;
import com.example.demo.vo.MemberVO;

public class DBManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
			
			inputStream.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static List<EmpVO> listEmp(){
		List<EmpVO> list =null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("emp.findAll");
		session.close();
		return list;
	}
	
	
	public static int getTotalRecord() {
		int n = 0;
		SqlSession session = sqlSessionFactory.openSession();
		n = session.selectOne("board.getTotalRecord");
		session.close();
		return n;
	}
	
	
	public static int update(BoardVO b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.update("board.update",b);
		session.commit();
		session.close();
		return re;
	}
	
	public static void  updateStep(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		session.update("board.updateStep", map);
		session.commit();
		session.close();
	}
	
	public static void  updateHit(int no) {
		SqlSession session = sqlSessionFactory.openSession();
		session.update("board.updateHit", no);
		session.commit();
		session.close();
	}
	
	
	public static BoardVO findByNo(int no) {
		BoardVO b = null;
		SqlSession session = sqlSessionFactory.openSession();
		b = session.selectOne("board.findByNo", no);
		session.close();
		return b;
	}
	
	public static List<BoardVO> findAll(HashMap<String, Object> map){
		List<BoardVO> list = null;
		SqlSession session= sqlSessionFactory.openSession();
		list = session.selectList("board.findAll", map);
		session.close();
		return list;
	}
	
	public static int insert(BoardVO b) {
		int re = -1;
		SqlSession session
		=sqlSessionFactory.openSession();
		re = session.insert("board.insert", b);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getNextNo() {
		int no = 0;
		SqlSession session = sqlSessionFactory.openSession();
		no = session.selectOne("board.getNextNo");
		session.close();
		return no;
	}

	public static int delete(HashMap<String, Object> map) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.delete("board.delete", map);
		session.commit();
		session.close();
		return re;
	}
	
	public static int insertMember(MemberVO m) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.insert("member.insert", m);
		session.commit();
		session.close();
		return re;
	}
	
	public static boolean isMember(HashMap<String, Object> map) {
		boolean re = false;
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO m = session.selectOne("member.isMember", map);
		if(m != null) {
			re = true;
		}
		session.close();
		return re;
	}
	
}



















