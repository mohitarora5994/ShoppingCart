<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<c:set var="context" value="${pageContext.request.contextPath}" />
<header id="header">
<script>
  window.fbAsyncInit = function() {
	  FB.init({
	    appId      : '811104175708264',
	    cookie     : true, 
	    xfbml      : true, 
	    version    : 'v2.8' 
	  });
	  FB.getLoginStatus(function(response) {
	    statusChangeCallback(response);
	  });

	  };
  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
</script>
<div class="header_top">
	<div class="logo col-sm-6">
		<a href="${context}"><img alt="logo" src="${context}/static/images/home/logo.png" height="50px" width="120px" ></a>
	</div>
	<div id="shopMenu" class="shop-menu pull-right">
		<ul class="nav navbar-nav">
			<li><a href="${context}/wishlist"><i class="fa fa-star"></i>WishList</a></li>
			<li><a href="${context}/MyCart"><i class="fa fa-shopping-cart"></i>Cart</a></li>
			<li class="signin"><a href="${context}/register">Register</a></li>
			<li class="logout" onclick="glogout();" ><a href="${context}/logout"><i class="fa fa-lock"></i>Logout</a></li>
			<li class="login"><a href="${context}/login"><i class="fa fa-lock"></i>Login</a></li>
		</ul>
	</div>
	<div class="user">
		<p style="color: white">Hello ${username}</p>
	</div>
	<div id="message" class="message">${message}</div>
</div>
</header>

	<div id="socialLogin" >
		<div class="sLogin" onclick="glogin();">
			<img alt="google" height="35px;" width="35px;"
			 src="static/images/home/google-icon.png">
			<b style="color: white;">Login Through Gmail</b>
		</div>
		<div class="sLogin" onclick="fbLogin();">
			<img alt="facebook" height="35px;" width="35px;"
			 src="static/images/home/facebook-icon.png">
			<b style="color: white;">Login Through Facebook</b>
		</div>
		<!-- <input type="button"  value="Logout" onclick="glogout();" /> -->
	</div>
<input id="username" type="hidden" value="${username}">
<input id="context" type="hidden" value="${pageContext.request.contextPath}">
<input id="loginType" type="hidden" >
<script type="text/javascript">
      (function() {
       var po = document.createElement('script'); 
		po.type = 'text/javascript'; 
		po.async = true;
       	po.src = 'https://apis.google.com/js/client.js?onload=onLoadCallback';
       	var s = document.getElementsByTagName('script')[0]; 
		s.parentNode.insertBefore(po, s);
     })();
</script>