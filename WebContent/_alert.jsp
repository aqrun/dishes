<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.aqrun.utils.*" %>
<%
List<String> list = FlushUtil.all(request);
for(String msg: list){
%>
<div class="alert alert-info" role="alert">
  <%=msg %>
</div>
<%}%>