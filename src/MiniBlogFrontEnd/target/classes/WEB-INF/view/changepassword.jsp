<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
	<h1>MiniBlog</h1>
	<h3>Change Password</h3>
	<table>
	${ErrorMessage}
	<form action="ChangePassword" method="post">
		<tr>
			<td>Password</td>
			<td><input type="password" maxlength="40" name="password"></td>
		</tr>
		<tr>
			<td>New Password</td>
			<td><input type="password" maxlength="40" name="newpassword"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Change Password"></td>
		</tr>
	</form>
	</table>
</body>
</html>