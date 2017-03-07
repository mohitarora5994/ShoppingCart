<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<header id="header">
<div class="header_top">
	<div class="logo col-sm-6">
		<a href="${context}/seller"><img alt="logo" src="${context}/static/images/home/logo.png" height="50px" width="120px" ></a>
	</div>
	<div id="shopMenu" class="shop-menu pull-right">
		<ul class="nav navbar-nav">
			<li><a>AddProducts</a></li>
			<li><a>viewProducts</a></li>
			<li></li>
			<li class="logout"><a href="${context}/logout"><i class="fa fa-lock"></i>Logout</a></li>
		</ul>
	</div>
	<div class="seller">
		<p style="color: white">Hello ${username}</p>
	</div>
	<div id="message" class="message">${message}</div>
</div>
</header>































<input id="username" type="hidden" value="${username}">
<input id="context" type="hidden" value="${pageContext.request.contextPath}">