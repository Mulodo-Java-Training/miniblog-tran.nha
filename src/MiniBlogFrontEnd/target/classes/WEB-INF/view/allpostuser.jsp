<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Post User</title>
</head>
<body>
	<h1>MiniBlog</h1>
	<h3>User's Posts </h3>
	<table>
		<c:forEach items="${Posts}" var="post">
			<tr>
				<td>Author</td>
				<td>${post.username}</td>
			</tr>
			<tr>
				<td>Title</td>
				<td><a href='<c:url value="PostDisplay?id=${post.id}"/>'>${post.title}</a></td>
			</tr>
			<tr>
				<td>Content</td>
				<td>${post.content}</td>
			</tr>
			<tr>
				<td>Create Date:</td>
				<td>${post.create_date} Last time modified at ${post.modify_date} </td>
			</tr>
			<tr>
				<c:if test="${SessionUser.id == post.authorID}">
					<td></td>
					<td>
						<form action="EditPost" method="post">
						<input type="hidden" name="postid" value="${post.id}"/>
						<input type="submit" value="Edit"></form>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>