<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://" + request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table align="center" width=90%>
<jsp:include page="nav.jsp"></jsp:include>
</table>

<div align="center"><h1>A beautiful UI</csdh1></div>
<table border="1" width=100%>
<tr><td rowspan=3><img width=100 height=100 src="<%=basePath%>/PicloadSvl?isbn=${abook.isbn}"/></td><td colspan=2 align=center style="color:red">${abook.name}</td></tr>
<tr><td>price</td><td>${abook.price}</td></tr>
<tr><td>publication</td><td>${abook.publication}</td></tr>
<tr><td height=300 colspan=3>${abook.description}</td></tr>
<tr><td colspan=3 align=center><a href="<%=basePath%>/user/ShoppingCartAdd?isbn=${abook.isbn}">add to shopping cart</a> &nbsp; <a href="<%=basePath%>/MainSvl">return</a></td></tr>
</table>
</body>
</html>