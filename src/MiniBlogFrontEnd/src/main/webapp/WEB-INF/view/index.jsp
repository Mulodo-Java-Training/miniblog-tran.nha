<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index Page</title>
<script type = "text/javascript" >
    history.pushState(null, null, 'login');
    window.addEventListener('popstate', function(event) {
    history.pushState(null, null, 'login');
    });
</script>
</head>
<body onload="javascript:window.history.forward(1);">
	<h1>MiniBlog</h1>
	<h3>Login</h3>
	<table>
	${ErrorMessage}
	<form action="Login" method="post">
		<tr>
			<td>Username</td>
			<td><input type="text" maxlength="40" name="username"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" maxlength="40" name="password"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Sign in"></td>
		</tr>
	</form>
	</table>
	<a href='<c:url value="../home/Register"/>'>Register New User</a>
</body>
</html>