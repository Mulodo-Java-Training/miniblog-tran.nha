<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result Search</title>
</head>
<body>
	<h1>Welcome to miniblog !!!</h1>
	<table>
		<c:forEach items="${Users}" var="user">
			<tr>
				<td> User: ${user.username}</td>
				<td>Firstname: ${user.firstname}</td>
				<td>Lastname: ${user.lastname}</td>
				<td>
					<form action='<c:url value="../Post/AllPostsUser"/>' method="Post">
					<input type="hidden" value="${user.id}" name="userid"/>
					<input type="submit" value="Get all post"/> 
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>