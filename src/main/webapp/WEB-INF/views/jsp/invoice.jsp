<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="invoice" style="border: solid; width: 50%;">
	<img alt="logo" src="${context}/static/images/home/logo.png" 
		height="50px" width="120px" style="margin-left: 20px;" >
		<hr>
		<div style="padding-left: 20px;">
		<b>Name: ${USERNAME}<br>
		Address: ${Address}<br>
		MobileNo:${MobileNo}<br>
		Date:${Date}</b>
		<hr>
		</div>

		<div style="margin-left: 20px;">
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
	<c:forEach var="products" items="${Quan}">
		<td>${products}</td>
	</c:forEach>
	</tr>
	<tr>
		<th>TotalPrice</th>
		<td>${total}</td>
	</tr>
</table>
</div>
	<div style="margin-left: 20px;">
	<br><br>
	PAYMENT MODE:${PaymentType}
	</div>
	
	<div class="last">
	<br><br>
			Copyright © 2013 E-SHOPPER Inc. All rights reserved.
			</div>
</div>


<a href="${context}/Order/${orderId}/downloadPDF" target="_blank">Download as pdf</a>





