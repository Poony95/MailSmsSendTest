package com.sist.util;

import com.example.demo.vo.EmpVO;

public class MyUtil {	
	
	public static String getHtml(EmpVO e) {
		String str = "";
		str += "<table border='1'>";
		str += "<tr>";
		str += "<td>사원번호</td>";
		str += "<td>사원명</td>";
		str += "<td>부서번호</td>";
		str += "<td>입사일</td>";
		str += "<td>급여</td>";
		str += "<td>수당</td>";
		str += "<td>실수령액</td>";		
		str += "</tr>";
		
		str += "<tr>";
		str += "<td>"+e.getEno()+"</td>";
		str += "<td>"+e.getEname()+"</td>";
		str += "<td>"+e.getDno()+"</td>";
		str += "<td>"+e.getHiredate()+"</td>";
		str += "<td>"+e.getSalary()+"</td>";
		str += "<td>"+e.getComm()+"</td>";
		str += "<td>"+(e.getSalary()+e.getComm())+"</td>";		
		str += "</tr>";
		str += "</table>";
		return str;
	}
	
	public static String getRenameNotMultiple(String oldName) {
		String fname = "";
		oldName = oldName.replace(".", ",");
		String []arr = oldName.split(",");
		try {
			long n = System.currentTimeMillis();
			fname = arr[0] + n + "." + arr[1];
		}catch (Exception e) {
			// TODO: handle exception
		}
		return fname;
	}
}
