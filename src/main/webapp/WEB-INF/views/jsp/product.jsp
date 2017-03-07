<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div>
<div class="productImg">
<img id="${Product.id}" class="product" src="${Product.url}" ><br>
<button class="wishlist" id="${Product.id}">WishList</button><br>
<button class="cart" id="${Product.id}">AddToCart</button>
</div>
<div class="productInfo">

<h3>${Product.modelName} ${Product.company} ${Product.category.categoryName}</h3>

<h4>&#8377 ${Product.price}</h4>
</div>
</div>
