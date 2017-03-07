<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<form style="float: right;" id="searchProducts" action="Products" method="get">
	<input name="Type" type="hidden" value="${Type}">
	
	<input name="Name" type="text" placeholder="Search By Name">
	<select name="company" id="company" >
		<option value="">Select by company</option>
		<c:forEach var="company" items="${productCompanyList}">
			<option>${company}</option>
		</c:forEach>
	</select>
</form>




<table>
	<c:forEach var="product" items="${productList}">
		<tr>
			<td><img class="product" id="${product.id}" src="${product.url}"></td>
		</tr>
		<tr>
			<td>${product.company}
			${product.modelName}</td>
		</tr>
		<tr>
			<td>RS.${product.price}</td>
		</tr>
		<tr>
			<td><button class="wishlist" id="${product.id}">WishList</button></td>
			<td><button class="cart" id="${product.id}">AddToCart</button></td>
		</tr>
	</c:forEach>
</table>