<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="orderDiv" style="border-style: solid; width: 250px; float: right;" >

<h4 style="padding-left: 70px;" >Your Order</h4>
<table  border="1">
	<tr>
		<th>ModelName</th>
	<c:forEach var="products" items="${productList}">
		<td>${products.modelName}</td>
	</c:forEach>
	</tr>
	<tr>
		<th>Price</th>
	<c:forEach var="products" items="${productList}">
		<td>${products.price}</td>
	</c:forEach>
	</tr>
	<tr>
		<th>Quantity</th>
	<c:forEach var="q" items="${quan}">
		<td>${q}</td>
	</c:forEach>
	</tr>
	<tr>
		<th>Delivery</th>
	<c:forEach var="q" items="${delivery}">
		<td>${q}</td>
	</c:forEach>
	</tr>
	<tr>
		<th>TotalPrice</th>
		<td>${total}</td>
	</tr>
</table>

<div id="addressSelected"></div>

<div id="checkout">
	<input type="hidden" id="addressId">
	<div id="paymentSelected"></div>
	<input type="button" value="Checkout">
</div>

</div>

<div style="border-style: groove; background-color: brown;width: 75%;">
	<p style="color: white">Select Address</p>
</div>

<c:forEach var="add" items="${Address}">
<div id="${add.id}" class="addressDb" style="border-style: solid; width: 222px;">	
${add.address}
</div>
<br><br>
</c:forEach>

<div class = "addAddress">Add Address</div>
<div class = "address">
<textarea id="address" rows="6" cols="25" style="resize: none;">
</textarea><br>
<button class="saveAddress">Save Address</button>
</div>
<div style="border-style: groove; background-color: brown; width: 75%;">
	<p style="color: white">Select Payment Mode</p>
</div>
<select id = "payy">
<c:forEach var="pay" items="${payment}">
<option value="${pay.id}">${pay.paymentType}</option>
</c:forEach>
</select>

