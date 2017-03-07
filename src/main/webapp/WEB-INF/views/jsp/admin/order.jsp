<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="Orders">
	<input type="submit" value="Pending" name="status">
	<input type="submit" value="Cancelled" name="status">
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
		<td>${o.status.status}</td>
		<td><a href="${pageContext.request.contextPath}/admin/Orders?id=${o.id}">Update</a></td>		
	</tr>
</c:forEach>
</table>
<div>
Order Status table
	<table>
		order table status display karna hai yaha
		usmai entry dalnne k liye methods b likhna hai
		usko edit delete karna yaha se
	id autogenerate rakha hai woh change karte hai apun hi provide karega
	</table>

</div>

<div>
Order list table yaha link dena hai aur kahi aur display  karna padega
usko edit delete karna yaha se
usmai entry dalnne k liye methods b likhna hai
</div>

<div>
	payment modes bhi yaha display karna padega ya dusre page par toh link yaha 
	usko edit delete karna yaha se
	id autogenerate rakha hai woh change karte hai apun hi provide karega
	usmai entry dalnne k liye methods b likhna hai
</div>


accounts ka table b banate hai aur apna profit likhte hai

spring sessions include karte hai..