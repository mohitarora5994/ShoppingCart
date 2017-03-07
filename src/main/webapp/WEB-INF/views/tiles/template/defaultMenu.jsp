<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table>
<c:forEach var="products" items="${Category}">
	<tr>
		<td><a href="${pageContext.request.contextPath}/Products?Type=${products.categoryName}">${products.categoryName}</a></td>
	</tr>
</c:forEach>
</table>



