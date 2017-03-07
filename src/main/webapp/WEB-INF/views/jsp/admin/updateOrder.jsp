<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="Orders">
	<input type="submit" value="Pending" name="status">
	<input type="submit" value="Canceled" name="status">
	<input type="submit" value="Delivered" name="status">
	<input type="submit" value="All">
</form>
<form>
	<input type="text" name="id" placeholder="searchById">
	<input type="submit" value="Search">
</form>
<table border="2" >
<th>OrderId</th>
<th>Username</th>
<th>ProductList</th>
<th>TotalPrice</th>
<th>DeliveryAddress</th>
<th>PaymentType</th>
<th>Status</th>
<th>Update</th>
<c:forEach var="o" items="${allOrder}">
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
		<td>
		<form action="UpdateOrder">
		<select name="status">
		<option>${o.status.status}</option>
		<c:forEach var="st" items="${allStatus}">
			<option>${st.status}</option>
		</c:forEach>
		</select>
		</td>
		<td>	<input type="hidden" name="orderId" value="${o.id}" >
				<input type="submit" value="Update" >
			</form>
		</td>
		
		
	</tr>
</c:forEach>
</table>