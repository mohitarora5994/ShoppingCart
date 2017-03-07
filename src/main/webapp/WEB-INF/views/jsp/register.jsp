<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
${error}
<table style="height: 300px; width: 100%;">
	<form:form commandName="userMaster" action="Register">
		<tr>
			<td>Name</td>
			<td><form:input path="name"/></td>
			<td><form:errors path="name"/></td>
		</tr>
		<tr>
			<td>email</td>
			<td><form:input path="email"/></td>
			<td><form:errors path="email"/></td>
		</tr>
		<tr>
			<td>Username</td>
			<td><form:input path="username"/></td>
			<td><form:errors path="username"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><form:password path="password"/></td>
			<td><form:errors path="password"/></td>
		</tr>
		<tr>
			<td>Re-type Password</td>
			<td><form:password path="rpassword"/></td>
			<td><form:errors path="rpassword"/></td>
		</tr>
		<tr>
			<td>mobileno</td>
			<td><form:input path="mobileno"/></td>
			<td><form:errors path="mobileno"/></td>
		</tr>
		<tr>
			<td><input type="reset" value="Reset"></td>
			<td><input type="submit" value="Submit"></td>
		</tr>
	</form:form>
	</table>