<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form action="${pageContext.request.contextPath}/admin/AddProduct" commandName="product" method="post" >

<table>
<tr>
	<td>Name</td>
	<td><form:input path="modelName"/></td>
	<td><form:errors path="modelName"/></td>
</tr>
<tr>
	<td>Url</td>
	<td><form:input path="url"/></td>
	<td><form:errors path="url"/></td>
</tr>
<tr>
	<td>Company</td>
	<td><form:input path="company"/></td>
	<td><form:errors path="company"/></td>
</tr>
<tr>
	<td>Price</td>
	<td><form:input path="price"/></td>
	<td><form:errors path="price"/></td>
</tr>
<tr>
	<td>Category</td>
	<td>
		<form:select path="category.category_id">
			<c:forEach var="products" items="${Category}">
				<form:option value="${products.category_id}" >${products.categoryName}</form:option>
			</c:forEach>
		</form:select>
	</td>
</tr>
<tr>
	<td><input type="reset" value="Reset"></td>
	<td><input type="submit" value="Submit"></td>
</tr>
</table>

</form:form>



<table border="1">
	<th>ID</th>
	<th>Name</th>
	<th>Company</th>
	<th>Price</th>
	<th>Category</th>
	<th>Update</th>
	<th>Delete</th>
	<th>Url</th>
	<c:forEach var="product" items="${productList}">
		<tr>
			<td>${product.id}</td>
			<td>${product.modelName}</td>
			<td>${product.company}</td>
			<td>${product.price}</td>
			<td>${product.category.categoryName}</td>
			<td><a href="${pageContext.request.contextPath}/admin/updateProduct/${product.id}">Update</a></td>
			<td><a href="${pageContext.request.contextPath}/admin/deleteProduct/${product.id}">Delete</a></td>
			<td>${product.url}</td>		
		</tr>
	</c:forEach>
</table>
