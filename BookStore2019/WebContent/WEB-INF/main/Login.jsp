<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://" + request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="ISO-8859-1">
<title>login</title>
</head>
<body>

<form action="<%=basePath %>/LoginSvl" method="POST">
<table align="center">
<tr height=200></tr>
<tr>
<td>User name</td><td><input type="text" name="uname"></td>
</tr>

<tr>
<td>Password</td><td><input type="password" name="pwd"></td>
</tr>

<tr align="center">
<td colspan="2"><input type="submit" value="Log in"></td>
</tr>

<tr align="center"><td colspan="2">${message}</td></tr>
</table>
</form>

</body>
</html>