<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<pre>
	Name:${User.name}
	UserName:${User.username}
	Mobile No:${User.mobileno}
	Email Id:${User.email}
</pre>
<a href="${context}/user/RemoveUser">Click Here to Delete user</a>
<h5 class="userUpdate">Click Here To Update</h5>



<div class="userInfo">
<table>
<form action="updateUser" method="post">
	<tr>
		<td>Name:</td>
		<td><input type="text" value="${User.name}" name="name"></td>
	</tr>
	<tr>
		<td>UserName</td>
		<td><input type="text" value="${User.username}" name="username"></td>
	</tr>
	<tr>
		<td>Mobile No</td>
		<td><input type="text" value="${User.mobileno}" name="mobileno"></td>
	</tr>
	<tr>
		<td>Email Id</td>
		<td><input type="text" value="${User.email}" name="email"></td>
	</tr>
	<tr>
		<td><input type="reset"></td>
		<td><input type="submit" value="Update"></td>
	</tr>
</form>
</table>
</div>