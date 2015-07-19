<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Post</title>
</head>
<body>
	<h1>Welcome to miniblog !!!</h1>
	<h3>Create new Post</h3>
	${ErrorMessage}
	<table>
		<form action="CreatePost" method="post">
			<tr>
				<td>Title</td>
				<td><input type="text" maxlength="100" name="title" required/></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><input type="text" maxlength="2000" name="content" required/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Create"/></td>
			</tr>
		</form>
	</table>
</body>
</html>