package com.aqrun.beans;

import java.util.*;

public class Favors {
	public static final int TYPE_XIANGLA = 1;
	public static final int TYPE_WEILA = 2;
	public static final int TYPE_MALA = 3;
	public static final int TYPE_BULA = 4;
	
	public static Map<Integer, Favor> dataList;
	
	static {
		dataList = new HashMap<Integer, Favor>();
		dataList.put(Favors.TYPE_XIANGLA, new Favor(Favors.TYPE_XIANGLA, "香辣"));
		dataList.put(Favors.TYPE_WEILA, new Favor(Favors.TYPE_WEILA, "微辣"));
		dataList.put(Favors.TYPE_MALA, new Favor(Favors.TYPE_MALA, "麻辣"));
		dataList.put(Favors.TYPE_BULA, new Favor(Favors.TYPE_BULA, "不辣"));
	}
	
	public static String getName(int id) {
		Favor f = dataList.get(id);
		if(f == null) return "";
		return f.getName();
	}
}
