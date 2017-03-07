<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="product" items="${Category}">

<li>${product.categoryName}</li>

</c:forEach>
<form method="post" action="AddProductType">
Add category<input type="text" name="type">
<input type="submit" value="Submit">
</form> 