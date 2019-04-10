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
<table align="center" width=90%>
<tr><td align="center" colspan=4><font color="red;size=18">Order completed! We will manage your order very soon!</font></td></tr>
<tr><td colspan=4 align="center">balance:$ ${user.balance} &nbsp;&nbsp;&nbsp;&nbsp; Total payment: $ ${totalPrice}</td></tr>
<tr><td align="center" colspan=4><a href="<%=basePath%>/MainSvl">Return back to Main Page</a></td></tr>
</table>

</body>
</html>