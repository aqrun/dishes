package com.aqrun.models;

import java.util.*;

import javax.servlet.ServletContext;

import com.aqrun.beans.*;

public class FoodModel {
	public static Map<Integer, Food> db;
	public static int maxIndex = 1;
	
	public static void init() {
		db = new HashMap<Integer, Food>();
		db.put(1, new Food(1, "宫抱鸡丁", Favors.TYPE_WEILA, "images/img1.jpg", 18.50, "好吃666"));
		db.put(2, new Food(2, "小鸡炖蘑菇", Favors.TYPE_MALA, "images/img2.jpg", 30.00, "哈哈强力推荐"));
		db.put(3, new Food(3, "红烧肉", Favors.TYPE_BULA, "images/img3.jpg", 20.10, "祖传做法"));
		db.put(4, new Food(4, "东坡肘子", Favors.TYPE_BULA, "images/img4.jpg", 49.34, "肥而不腻、粑而不烂、咸甜适口，色、香、味、形俱佳 ，曾被外宾赞颂可列入世界名菜。 对于我等嗜肉一族，东坡肘子的诱惑，谁又能 hold 得住？"));
		maxIndex = 4;
	}
	
	public static void add(ServletContext servletContext,Food food) {
		Integer max_index = (Integer)servletContext.getAttribute("max_index");
		Map<Integer, Food> dataList = (HashMap<Integer, Food>)servletContext.getAttribute("db");
		food.setId(max_index += 1);
		dataList.put(max_index, food);
		servletContext.setAttribute("db", dataList);
		servletContext.setAttribute("max_index", max_index);
	}
	
	public static void update(ServletContext ctx,Integer id, Food newFood) {
		Map<Integer, Food> dataList = (HashMap<Integer, Food>)ctx.getAttribute("db");
		dataList.remove(id);
		dataList.put(id, newFood);
		ctx.setAttribute("db", dataList);
	}
	
	public static void delete(ServletContext ctx,int id) {
		Map<Integer, Food> dataList = (Map<Integer, Food>)ctx.getAttribute("db");
		dataList.remove(id);
		ctx.setAttribute("db", dataList);
	}
	
	public static Map<Integer, Food> getAllFood(ServletContext cxt){
		Map<Integer, Food> dataList = (Map<Integer, Food>)cxt.getAttribute("db");
		return dataList;
	}
	
	public static Map<Integer, Food> findFoodByName(ServletContext cxt, String name){
		Map<Integer, Food> dbList = (Map<Integer, Food>)cxt.getAttribute("db");
		Map<Integer, Food> dataList = new HashMap<Integer, Food>();
		for(int k: dbList.keySet()) {
			Food f = dbList.get(k);
			if(f.getName().contains(name)) {
				dataList.put(k, f);
			}
		}
		return dataList;
	}
	
	public static Food findFoodById(ServletContext cxt, Integer id){
		Map<Integer, Food> dbList = (Map<Integer, Food>)cxt.getAttribute("db");
		return dbList.get(id);
	}
}
