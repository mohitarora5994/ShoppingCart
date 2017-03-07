<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id = "body" class="body">
<table border="1" style="width: 100%;">
<th>IMAGE</th>	<th>ITEM</th>	<th>PRICE(RS.)</th>	<th>QUANTITY</th>	<th>DELIVERY DETAILS</th>	<th>SUBTOTAL</th>
	<c:forEach var="product" items="${Cart}">
		<tr>
			<td>
				<img class="product" id="${product.id}" src="${product.url}"><br/>
				<button id="${product.id}" class="RemoveFromCart">RemoveFromCart</button>
			</td>
			
			<td>
				${product.modelName}<br>
				${product.company}<br>
				${product.category.categoryName}<br>
				<input  name ="product" field="${product.id}" class="price${product.id}" type="hidden" value="${product.price}">				
			</td>
			
			<td>${product.price}</td>
			
			<td>    
				<input type='button' id="${product.id}" value='-' class='qtyminus' field='quantity${product.id}'  />
  			 	<input type='text' size="2"	 id="${product.id}" value='1' class='qty' name='quantity${product.id}'  />
   			 	<input type='button' id="${product.id}" value='+' class='qtyplus' field='quantity${product.id}' />
			</td>
			
			<td class="delivery${product.id}">Bhai tere liye free hai.</td>
			
			<td><div id="price${product.id}" class ="subtotal">${product.price}</div></td>
		</tr>
			</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td>Estimated price</td>
			<td></td>
			<td><div class="total"></div></td>
		</tr>
</table>
		<button id="home">Continue Shopping</button>
		<button id="order">Place Order</button>
		
	</form>

</div>