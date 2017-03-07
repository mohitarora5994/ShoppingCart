	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="${pageContext.request.contextPath}/admin/updateProduct" commandName="product" method="post" >

<table>
<tr>
	<td>Id</td>
	<td><form:input path="id" readonly="true" /></td>
	<td><form:errors path="id" /></td>
</tr>
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
	<td></td>
	<td><input type="submit" value="Submit"></td>
</tr>
</table>

</form:form>