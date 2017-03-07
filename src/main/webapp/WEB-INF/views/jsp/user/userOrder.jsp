<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="Order">

	<h3 class="CO" >Click Here To Cancel Order</h3>
	<div class="cancelOrder">
		<table border="2" >
			<th>OrderId</th>
			<th>Username</th>
			<th>ProductList</th>
			<th>TotalPrice</th>
			<th>DeliveryAddress</th>
			<th>PaymentType</th>
			<th>Status</th>
			<th>Click Here to View Order</th>
			<th>Click Here to Cancel Order</th>
		<c:forEach var="o" items="${pendingOrder}">
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
				<td id="${o.id}" class="orderKPage">View Order</td>
				<td id="${o.id}" class="cancelKPage" ><a href="${context}/user/Order/${o.id}/CancelOrder">CancelOrder</a></td>
			</tr>
		</c:forEach>
	</table>
</div>


<h3 class="oo">Orders</h3>
<div class="viewOrders">
<table border="2" >
<th>OrderId</th>
<th>Username</th>
<th>ProductList</th>
<th>TotalPrice</th>
<th>DeliveryAddress</th>
<th>PaymentType</th>
<th>Status</th>
<th>Click Here to View Order</th>
<c:forEach var="o" items="${Order}">
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
		<td id="${o.id}" class="orderKPage">View Order</td>
	</tr>
</c:forEach>
</table>
</div>
<h3 class="so">Return Orders</h3>
<div class="returnOrder">
<table border="2" >
<th>OrderId</th>
<th>Username</th>
<th>ProductList</th>
<th>TotalPrice</th>
<th>DeliveryAddress</th>
<th>PaymentType</th>
<th>Status</th>
<th>Click Here to View Order</th>
<c:forEach var="o" items="${Order}">
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
		<td id="${o.id}" class="orderKPage">View Order</td>
	</tr>
</c:forEach>
</table>
</div>
</div>