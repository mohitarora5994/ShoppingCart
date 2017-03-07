<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><tiles:getAsString name="title"/></title>
</head>
<body>
	<div><tiles:insertAttribute name="head" /></div>
	<div style="position: fixed;width: 100%;" ><tiles:insertAttribute name="header" /></div>
	
	<div style="float: left;padding: 10px;width: 15%;padding-top: 100px;">
		<tiles:insertAttribute name="menu" />
	</div>
	
	<div style="padding-top:110px; padding-left:20px; padding-bottom:20px; float:left; width:80%;border-left:1px solid pink;">
		<tiles:insertAttribute name="body" />
	</div>
	
	<div style="clear:both">
		<tiles:insertAttribute name="footer" />
	</div>	
	
</body>
</html>