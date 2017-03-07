<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<table border="2" >
<th>OrderId</th>
<th>Username</th>
<th>ProductList</th>
<th>TotalPrice</th>
<th>DeliveryAddress</th>
<th>PaymentType</th>
<th>Status</th>

	<tr>
		<td>${o.id}</td>
		<td>${o.user.name}</td><%-- <td>${o.user.username}</td> --%>
		<td><table border="1">
			<th>Name</th>
			<th>Price</th>
			<c:forEach var="c" items="${o.product}">
				<tr>
					<td>${c.modelName}</td>
					<td>${c.price}</td>
				</tr>		
			</c:forEach>
			</table></td>
		<td>${o.totalPrice}</td>
		<td width="30">${o.address}</td>
		<td>${o.payment.paymentType}</td>
		<td>${o.status.status}</td>
	</tr>
</table>
<form action="${context}/user/ConfirmCancel" method="post">
	<input type="hidden" name="id" value="${o.id}">
	<b>Reason toh bata Cancel karne ka</b><br>
<select name="reason">
		<option>No Reason</option>
	<c:forEach var = "a" items="${reasons}">
		<option>${a.reason}</option>
	</c:forEach>
</select><br>
<input type="submit" value="ConfirmCancel">
</form>
