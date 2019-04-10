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
<tr><td align=right>

<c:if test="${user==null}">
<a href = "<%=basePath%>/LoginSvl">Login</a>
</c:if>

<c:if test="${user!=null}">
Welcome you, ${user.uname}
<a href="<%=basePath%>/user/ShoppingCartSvl">Shopping Cart</a>
<a href="<%=basePath%>/LogoutSvl">Logout</a>
<c:if test="${user.role == 1}">
<a href = '#'>admin</a>
</c:if>
</c:if>

</td></tr>
</body>
</html>