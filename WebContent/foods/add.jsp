<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp"%>
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
			<h1 class="h2">Add new food</h1>
		</div>

		<div class="row">
			<div class="col-md-8">
				<form method="post" action="<%=basePath%>foods-handle" class="form form_food">
					<input type="hidden" name="action" value="add" />
					<!-- <div class="form-group row">
						<label class="col-sm-2 col-form-label"><span class="red">*</span> ID</label>
						<div class="col-sm-5">
							<input type="text" class="form-control ipt_name"
								name="id" value="">
						</div>
					</div> -->
					<div class="form-group row">
						<label class="col-sm-2 col-form-label"><span class="red">*</span> 菜名</label>
						<div class="col-sm-5">
							<input type="text" class="form-control ipt_name"
								name="name" value="">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">口味</label>
						<div class="col-sm-10">
							<%
							Map<Integer, Favor> map = Favors.dataList;
							for(int id: map.keySet()){ 
								Favor f = map.get(id);
							%>
							<div class="form-check form-check-inline">
								<input class="form-check-input ipt_favor" type="radio"
									id="inlineCheckbox<%=id%>"
									name="favor" value="<%=f.getId()%>"> 
								<label class="form-check-label" for="inlineCheckbox<%=id%>"><%=f.getName() %></label>
							</div>
							<%} %>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">图片</label>
						<div class="col-sm-10">
							<div class="row">
							<div class="col-sm-8">
								<input type="text" class="form-control ipt_thumb"
									name="thumb" value="">
							</div>
							<div class="col-sm-4">
								<div class="file_upload_w">
									<div class="row">
										<div class="col-sm-8">
											<span class="btn btn-success fileinput-button">
										        <i class="glyphicon glyphicon-plus"></i>
										        <span>Select file</span>
										        <input id="fileupload" type="file" name="file" multiple>
									    	</span>
										    <div id="progress" class="progress" style="height:5px;margin-top:5px;">
										        <div class="progress-bar progress-bar-success"></div>
										    </div>
										</div>
										<div class="col-sm-4">
											<img src="" class="img_preview" style="max-width:80px;"/>
										</div>
									    
									</div>
									
								</div>
							</div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label"><span class="red">*</span> 价格</label>
						<div class="col-sm-3">
							<input type="text" class="form-control ipt_price"
								name="price" value="">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">描述</label>
						<div class="col-sm-10">
							<textarea class="form-control ipt_desc" rows="3" name="description"></textarea>
						</div>
					</div>
					<div class="form-group row">
					    <div class="col-sm-10 offset-sm-2">
					      <button type="submit" class="btn btn-primary btn_food_submit">保存</button>
					    </div>
					 </div>
				</form>
			
			</div>
		</div>
		

		<div style="height: 200px;"></div>

		</main>
	</div>
</div>
<%@ include file="../_footer.jsp"%>