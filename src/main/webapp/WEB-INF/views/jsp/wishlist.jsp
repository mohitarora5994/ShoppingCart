<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
<c:forEach var="product" items="${WishList}">
		<tr>
			<td><img class="product" id="${product.id}" src="${product.url}"></td>
			<td>
				<table>
					<tr><td>${product.modelName}</td></tr>
					<tr><td>${product.company}</td></tr>
					<tr><td>RS.${product.price}</td></tr>
					<tr><td>${product.category.categoryName}</td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<form action="removeFromWishList" method="post">
				<input type="hidden" value="${product.id}" name="id" >
				<input type="submit" value="Remove From WishList">
				</form>
			</td>
					
			<td><button class="cart" id="${product.id}">AddToCart</button></td>
		</tr>
		
	</c:forEach>
</table>