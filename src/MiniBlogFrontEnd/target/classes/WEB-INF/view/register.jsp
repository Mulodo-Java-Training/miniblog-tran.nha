<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Page</title>
</head>
<body>
	<h1>MiniBlog</h1>
	<h3>Register</h3>
	${ErrorMessage}
	<table>
		<form action="Register" method="post">
			<tr>
				<td>Username</td>
				<td><input type="text" maxlength="40" name="username"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" maxlength="40" name="password"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" maxlength="50" name="email"></td>
			</tr>
			<tr>
				<td>Lastname</td>
				<td><input type="text" maxlength="50" name="lastname"></td>
			</tr>
			<tr>
				<td>Firstname</td>
				<td><input type="text" maxlength="50" name="firstname"></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>
					<select name="gender">
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Birthday</td>
				<td><input type="text" maxlength="10" name="birthday"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Register"></td>
			</tr>
		</form>
	</table>
</body>
</html>