<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="accountOrder">
	<h3 style="color: orange;">Orders</h3>
	Track, Return or Cancel an Order<br>
	<button class="uOrder" style="background-color: gold;">Your Orders</button>
</div>


<div id="accountSetting">
	<h3 style="color: orange;">Account Setting</h3>
	<a href="${context}/user/userAccount"><b>Login & Security Settings</b></a>
	<div>
		<b>Address Book</b><br>
		<a href = "${context}/user/address" >View Address</a><br>
		<a href="${context}/user/saveAddress">Add Address</a>
	</div>
</div>

