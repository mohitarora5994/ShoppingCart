<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div>
<h2>Latest Products</h2>
<c:forEach var="p" items="${latestProducts}">
<img id="${p.id}" class="product" src="${p.url}" >
</c:forEach>
</div>


<div>
<img id="img" onload="startTimer();" src="${context}/static/images/adv/adv3.jpg" height="400" />
<%-- <table>
	<c:forEach var="product" items="${productList}">
		<tr>
			<td><img src="${product.url}" height="160px" width="140px"></td>
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
</table> --%>
</div>