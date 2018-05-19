<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@ page import="com.aqrun.beans.*, com.aqrun.models.*, java.util.*"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="../_sidebar.jsp"%>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
			<div class="chartjs-size-monitor"
				style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
				<div class="chartjs-size-monitor-expand"
					style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
					<div
						style="position: absolute; width: 1000000px; height: 1000000px; left: 0; top: 0"></div>
				</div>
				<div class="chartjs-size-monitor-shrink"
					style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
					<div
						style="position: absolute; width: 200%; height: 200%; left: 0; top: 0"></div>
				</div>
			</div>
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
				<h1 class="h2">Foods List</h1>
				<div class="btn-toolbar mb-2 mb-md-0">
					<a role="button" class="btn btn-primary"
						href="<%=basePath%>foods-handle?action=add">Add new food</a>
				</div>
			</div>

			<h2>Foods List</h2>
			<%@ include file="../_alert.jsp" %>
			
			<% String filter_name = request.getParameter("name");  %>
			<div class="" style="margin:20px 0;padding:25px;border:1px solid #d1d1d1;background:#f1f1f1;">
				<div class="row">
					<div class="col-sm-1" style="text-align:right;">菜品名:</div>
					<div class="col-sm-8">
						<input class="form-control ipt_filter_name" value="<%=filter_name==null?"":filter_name%>"/>
					</div>
				</div>
				<div class="row" style="margin-top:15px;">
					<div class="col-sm-10 offset-sm-1" style="text-align:left;">
						<button class="btn btn-sm btn-primary btn_filter" 
							data-url="<%=basePath%>foods/index.jsp">Filter</button>
						<a class="btn btn-info btn-sm" href="<%=basePath%>foods/index.jsp">Reset</a>
					</div>
				</div>
			</div>
			
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th style="width:80px;">#</th>
							<th style="width:20%;">菜名</th>
							<th style="width:120px;">缩略图</th>
							<th style="width:80px;text-align:center;">口味</th>
							<th style="width:80px;">价格</th>
							<th>描述</th>
							<th style="width:100px">操作</th>
						</tr>
					</thead>
					<tbody>
						<% 
		              	Map<Integer, Food> map = FoodModel.getAllFood(getServletContext());
						boolean haveData = false;
		              	for(int k: map.keySet()){ 
		              		Food f = map.get(k);
		              		if(filter_name!=null && !f.getName().contains(filter_name)){
		              			continue;
		              		}
		              		haveData = true;
		              	%>
						<tr>
							<td><%=f.getId() %></td>
							<td><%=f.getName() %></td>
							<td>
								<% if(!f.getThumb().equals("")){ %>
								<img src="<%=basePath + f.getThumb() %>" style="max-width:100px;"/>
								<%} %>
							</td>
							<td style="text-align:center;">
								<%= Favors.getName(f.getFavor()) %>
							</td>
							<td><%=f.getPrice() %> 元</td>
							<td><%=f.getDescription() %></td>
							<td>
								<div class="btn-group" role="group" aria-label="Basic example">
									<a role="button" class="btn btn-light"
										href="<%=basePath %>foods-handle?action=update&id=<%=f.getId()%>"
										> <i
										class="icon-pencil"></i>
									</a> <a role="button" class="btn btn-danger btn_food_delete"
										data-id="<%=f.getId() %>"
										data-name="<%=f.getName()%>"
										> <i
										class="icon-trash"></i>
									</a>
								</div>
							</td>
						</tr>
						<%} %>
					</tbody>
				</table>
				
				<% if(!haveData){ %>
				<div class="alert alert-light" style="text-align:center;">No Data</div>
				<%} %>
			</div>


			<div style="height: 200px;"></div>



			</main>
		</div>
	</div>

<%@ include file="../_footer.jsp" %>