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

<div align="center"><h1>A beautiful UI</h1></div>
<table border="1" width=100%>
<c:forEach
    items="${books}"
    var="aBook"
>
    <tr><td rowspan=3><img width=100 height=100 src="<%=basePath%>/PicloadSvl?isbn=${aBook.isbn}"></td>
    <td colspan=2 align=center style="color:red"><a href="<%=basePath%>/BookDetailSvl?isbn=${aBook.isbn}">${aBook.name}</a></td></tr>
    <tr><td>Price</td><td>${aBook.price}</td></tr>
    <tr><td>Publication</td><td>${aBook.publication}</td></tr>
</c:forEach>
</table>
</body>
</html>