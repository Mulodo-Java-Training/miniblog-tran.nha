<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	<table>
		<tr>
			<td><a href='<c:url value="../User/Welcome"/>'>Home</a></td>
			<td><a href='<c:url value="../User/ChangePassword"/>'>Change Password</a></td>
			<td><a href='<c:url value="../User/UpdateInfo"/>'>Update Info</a></td>
			<td><a href='<c:url value="../User/LogOut"/>'>Log Out</a></td>
		</tr>
	</table>
	<h1>Welcome to miniblog !!!</h1>
		${SystemMessage}
	<table>
		<form action="SearchName" method="post">
		<tr>
			<td><input type="text" maxlength="40" name="namesearch"/></td>
			<td><input type="submit" value="Search"/></td>
		</tr>
		</form>
		${ErrorMessage}
	</table>
	<a href='<c:url value="../Post/CreatePost"/>'>Create New Post</a>
	<h4>Top New Post</h4>
	<table>
		<c:forEach items="${Posts}" var="post">
			<c:if test="${post.status == 'Available' || SessionUser.id == post.authorID }">
			<tr>
				<td>Author</td>
				<td>${post.username}</td>
			</tr>
			<tr>
				<td>Title</td>
				<td><a href='<c:url value="../Post/PostDisplay?id=${post.id}"/>'>${post.title}</a></td>
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
						<form action="<c:url value="../Post/EditPost"/>" method="post">
						<input type="hidden" name="postid" value="${post.id}"/>
						<input type="submit" value="Edit"></form>
					</td>
					<td>
						<form action="<c:url value="../Post/ChangeStatus"/>" method="post">
								<input type="hidden" value="${post.id}" name="postid"/>
								<input type="submit" value="Change Status"/>
						</form>
					</td>
					<c:if test="${post.status == 'Delete'}">
						<td>
							<form action="<c:url value="../Post/DeletePost"/>" method="post">
								<input type="hidden" value="${post.id}" name="postid"/>
								<input type="submit" value="X"/>
							</form>
						</td>
					</c:if>
				</c:if>
			</tr>
			</c:if>
		</c:forEach>
	</table>
</body>
</html>