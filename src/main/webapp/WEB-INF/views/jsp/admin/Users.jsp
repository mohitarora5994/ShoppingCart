<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table border="2">
	<th>ID</th>
	<th>Name</th>
	<th>Username</th>
	<th>Email</th>
	<th>mobileno</th>
	<th>password</th>
<c:forEach var="user" items="${userList}">
<tr>
	<td>${user.id}</td>
	<td>${user.name}</td>
	<td>${user.username}</td>
	<td>${user.email}</td>
	<td>${user.mobileno}</td>
	<td>${user.password}</td>
</tr>
</c:forEach>
</table>