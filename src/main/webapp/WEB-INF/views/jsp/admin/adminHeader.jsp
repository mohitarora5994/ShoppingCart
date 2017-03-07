<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="header_top">
	<div class="logo col-sm-6">
		<a href="${context}/admin"><img alt="logo" src="${context}/static/images/home/logo.png" height="50px" width="120px" ></a>
	</div>
	<div id="shopMenu" class="shop-menu pull-right">
		<ul class="nav navbar-nav">
			<li><a href="${context}/admin/AddProducts">Add products</a></li>
			<li><a href="${context}/admin/AddProductType">Add Category</a></li>
			<li><a href="${context}/admin/Users">View User</a></li>
			<li><a href="${context}/admin/Orders">View Order</a></li>
			<li class="logout"><a href="${context}/logout"><i class="fa fa-lock"></i>Logout</a></li>
		</ul>
	</div>
	<div class="user">
		<p style="color: white">Hello ${username}</p>
	</div>
		<div id="message" class="message">${message}</div>
	</div>
</div>
<input id="username" type="hidden" value="${username}">
<input id="context" type="hidden" value="${pageContext.request.contextPath}">
<input id="loginType" type="hidden" >
