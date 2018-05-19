package com.aqrun.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

public class FlushUtil {
	public static final String SESSION_FLUSH = "flush_data";
	
	public static void add(HttpServletRequest request,String msg) {
		HttpSession session = request.getSession(true);
		List<String> dataList;
		Object sessionData = session.getAttribute(FlushUtil.SESSION_FLUSH);
		if(sessionData == null) {
			dataList = new ArrayList<String>();
		}else {
			dataList = (List<String>)sessionData;
		}
		
		dataList.add(msg);
		session.setAttribute(FlushUtil.SESSION_FLUSH, dataList);
	}
	
	public static List<String> all(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		ArrayList<String> dataList;
		Object sessionData = session.getAttribute(FlushUtil.SESSION_FLUSH);
		if(sessionData == null) {
			dataList = new ArrayList<String>();
		}else {
			dataList = (ArrayList<String>)sessionData;
		}
		//dataList.add("test");
		List<String> data = (ArrayList<String>)dataList.clone();
		dataList.clear();
		session.setAttribute(FlushUtil.SESSION_FLUSH, dataList);
		return data;
	}
}
