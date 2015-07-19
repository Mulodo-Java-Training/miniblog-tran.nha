<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Information</title>
</head>
<body>
	<h1>MiniBlog</h1>
	<h3>Update Information</h3>
	<table>
	${ErrorMessage}
	<form action="UpdateInfo" method="post">
		<tr>
			<td>Username</td>
			<td>${SessionUser.username}</td>
		</tr>
		<tr>
			<td>Lastname</td>
			<td><input type="text" maxlength="40" name="lastname" value="${SessionUser.lastname}"/></td>
		</tr>
		<tr>
			<td>Firstname</td>
			<td><input type="text" maxlength="40" name="firstname" value="${SessionUser.firstname}"/></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" maxlength="40" name="email" value="${SessionUser.email}"/></td>
		</tr>
		<tr>
			<td>Gender</td>
			<td><input type="text" maxlength="40" name="gender" value="${SessionUser.gender}"/></td>
		</tr>
		<tr>
			<td>Create date</td>
			<td>${SessionUser.create_date}</td>
		</tr>
		<tr>
			<td></td>
			<td>Last time modified at ${SessionUser.modify_date}</td>
		</tr>
		<tr>
			<td>Enter Password</td>
			<td><input type="password" maxlength="40" name="password"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Update"></td>
		</tr>
	</form>
	</table>
</body>
</html>