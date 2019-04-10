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
<form action="<%=basePath%>/user/CheckoutSvl" method="Post">
<table border="1" width=90% align="center">
<tr><td>Book Name</td><td>Author</td><td>Price</td><td width="5%">Amount</td><td >Operation</td></tr>
<c:forEach var="abook" items="${shoppingCart}">
<tr><td>${abook.name}</td><td>${abook.author}</td><td>${abook.price}</td><td><input type="text" name="${abook.isbn}" value="1"></td>
<td><a href="<%=basePath%>/user/RemoveCartSvl?isbn=${abook.isbn}">Remove</a></td></tr>
</c:forEach>
<tr><td align="center" colspan=5><input type="submit" value="check out"><a href="<%=basePath%>/MainSvl">Return</a></td></tr>
</table>
</form>>
</body>
</html>