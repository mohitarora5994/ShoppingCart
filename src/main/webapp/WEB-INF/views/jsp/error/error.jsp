<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="/WEB-INF/views/tiles/template/defaultHead.jsp" />
<jsp:include page="/WEB-INF/views/tiles/template/defaultHeader.jsp" />
	<div style="float: left;padding: 10px;width: 15%;padding-top: 100px;">	
	</div>
	<div style="padding-top:110px; padding-left:20px; padding-bottom:20px; float:left; width:80%;border-left:1px solid pink;">
		<h4>Something went wrong bro</h4>
		<img src="${context}/static/images/404/404.png" height="300px;">
	</div>
	
	<div style="clear:both">
		<jsp:include page="/WEB-INF/views/tiles/template/defaultFooter.jsp" />
	</div>
	

