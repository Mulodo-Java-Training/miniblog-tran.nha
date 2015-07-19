<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Post</title>
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
	<h1>MiniBlog</h1>
	<h3>Post Details</h3>
	${SystemMessage}
	<br/>
	<table>
		<tr>
			<td>Title</td>
			<td>${Post.title}</td>
		</tr>
		<tr>
			<td></td>
			<td>${Post.content}</td>
		</tr>
		<tr>
			<td></td>
			<td>Create by ${Post.username} at ${Post.create_date} (Modified at ${post.modify_date})</td>
		</tr>
	</table>
	<br/>
	<table>
		<c:forEach items="${Comments}" var="comment">
			<c:if test='${comment.status == "Available" || SessionUser.id == comment.authorID}'>
				<tr>
					<td>${comment.username}</td><td>${comment.status}</td>
				</tr>
				<tr>
					<td>${comment.content}</td>
				</tr>
				<tr>
					<td>${comment.create_date}</td>
				</tr>
			<c:if test="${SessionUser.id == comment.authorID}">
					<tr>
						<td>
						<form action="<c:url value="../Comment/EditComment"/>" method="post">
							<input type="text" maxlength="500" name="content" required/>
							<input type="hidden" value="${comment.id}" name="commentid"/>
							<input type="submit" value="Edit"/>
						</form>
						</td>
				
						<td>
							<form action="<c:url value="../Comment/ChangeStatus"/>" method="post">
								<input type="hidden" value="${comment.id}" name="commentid"/>
								<input type="submit" value="Change Status"/>
							</form>
						</td>
					<c:if test="${comment.status == 'Delete'}">
							<td>
								<form action="<c:url value="../Comment/DeleteComment"/>" method="post">
									<input type="hidden" value="${comment.id}" name="commentid"/>
									<input type="submit" value="X"/>
								</form>
							</td>
						</c:if>
					</tr>
				</c:if>
			</c:if>
		</c:forEach>
	</table>
	<br/>
	<table>
		Create new comment
		<tr>
			<form action='<c:url value="../Comment/CreateNewComment"/>' method="post">
			<td><input type="text" name="content" required/><input type="hidden" name="postid" value="${Post.id}"/></td>
			<td><input type="submit" value="Create Comment"/></td>
			</form>
		</tr>
	</table>
</body>
</html>