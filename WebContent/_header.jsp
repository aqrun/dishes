
<% String basePath = request.getContextPath()+"/"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Dishes</title>
	
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link href="<%=basePath %>plugins/Font-Awesome-3.2.1/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=basePath%>plugins/jQuery-File-Upload-9.21.0/css/jquery.fileupload.css">
	<link rel="stylesheet" href="<%=basePath %>css/style.css?v=0.11"/>
	<script>
		window.g = window.g || {};
		g.basePath = "<%=basePath%>";
	</script>
</head>
<body>
	
	<%@ include file="_header_top.jsp" %>