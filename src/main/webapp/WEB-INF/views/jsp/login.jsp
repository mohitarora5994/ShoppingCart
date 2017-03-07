<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form method="post" action="Login" commandName="loginForm">
${error}
<table>
		<tr>
			<td>UserName<td>
			<td><form:input path="username"/></td>
			<td><form:errors path="username" /></td>
		</tr>
		<tr>
			<td>Password<td>
			<td><form:password path="password" /></td>
			<td><form:errors path="password" /></td>
		</tr>
		<tr>
			<td><input type="reset" value="Reset"><td>
			<td><input type="submit" value="Submit"></td>
		</tr>
	</table>
</form:form>