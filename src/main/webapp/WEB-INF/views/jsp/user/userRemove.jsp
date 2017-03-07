<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<form action="${context}/user/RemoveUser/ConfirmRemoveUser" method="post">
	Enter password: <input type="password" name="password"><br>
	<input type="submit" value="Submit">
</form>