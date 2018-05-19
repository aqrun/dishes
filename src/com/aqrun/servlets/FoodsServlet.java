package com.aqrun.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aqrun.beans.Food;
import com.aqrun.models.FoodModel;
import com.aqrun.utils.FlushUtil;

/**
 * Servlet implementation class FoodsServlet
 */
@WebServlet("/foods-handle")
public class FoodsServlet extends HttpServlet {
	
	public void init() throws ServletException {
		FoodModel.init();
		this.getServletContext().setAttribute("db", FoodModel.db);
		this.getServletContext().setAttribute("max_index", FoodModel.maxIndex);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String basePath = request.getContextPath()+"/";
		try {
			String action = request.getParameter("action");
			switch(action) {
			case "add":
				request.getRequestDispatcher("foods/add.jsp").forward(request, response);
				break;
			case "update":
				request.getRequestDispatcher("foods/edit.jsp").forward(request, response);
				break;
			default:
				response.sendRedirect(basePath + "index.jsp");
				break;
			}
		} catch(Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String basePath = request.getContextPath()+"/";
		String action = request.getParameter("action");
		switch(action) {
		case "add":
			actionAdd(request, response);
			break;
		case "update":
			actionUpdate(request, response);
			break;
		case "delete":
			actionDelete(request, response);
			break;
		}
	}
	
	protected void actionAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String basePath = request.getContextPath()+"/";
		//String id = request.getParameter("id");
		String name = request.getParameter("name");
		String favor = request.getParameter("favor");
		String thumb = request.getParameter("thumb");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		try {
			if(name.trim().equals("") || name == null
					|| price.trim().equals("") || price==null
					) {
				FlushUtil.add(request, "name and price is required");
				response.sendRedirect(basePath + "foods-handle?action=add");
			}
			
			Food food = new Food(1000,name, Integer.parseInt(favor), thumb, 
					Double.parseDouble(price), description);
			FoodModel.add(this.getServletContext(), food);
			FlushUtil.add(request, "Save success");
			
			response.sendRedirect(basePath + "foods/index.jsp");
		} catch(Exception e) {
			FlushUtil.add(request, e.getMessage());
			response.sendRedirect(basePath + "foods/index.jsp");
		}
	}
	protected void actionUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String basePath = request.getContextPath()+"/";
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String favor = request.getParameter("favor");
		String thumb = request.getParameter("thumb");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		try {
			if(name.trim().equals("") || name == null
					|| price.trim().equals("") || price==null
					) {
				FlushUtil.add(request, "name and price is required");
				response.sendRedirect(basePath + "foods-handle?action=add");
				return;
			}
			int id1 = Integer.parseInt(id);
			Food food = new Food(id1,name, Integer.parseInt(favor), thumb, 
					Double.parseDouble(price), description);
			FoodModel.update(this.getServletContext(),id1, food);
			FlushUtil.add(request, "update success");
			response.sendRedirect(basePath + "foods/index.jsp");
		} catch(Exception e) {
			FlushUtil.add(request, e.getMessage());
			response.sendRedirect(basePath + "foods/index.jsp");
		}
	}
	protected void actionDelete(HttpServletRequest request, HttpServletResponse response) {
		//response.setContentType("text/json; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			
			String id = request.getParameter("id");
			if(id.equals("") || id == null) {
				out.print(0);
			}
			FoodModel.delete(this.getServletContext(), Integer.parseInt(id));
			out.print(1);
		} catch(Exception e) {
			
		}
		
	}

}
