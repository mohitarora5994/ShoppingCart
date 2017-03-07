function fbLogin(){
/*	FB.getLoginStatus(function(response){ 
		if (response.status === 'connected') 
		{
		      testAPI();
		 }		
	});*/
	 FB.login(function(response) {
         if (response.authResponse) 
         {
              testAPI();
          } else 
          {
           console.log('User cancelled login or did not fully authorize.');
          }
       },{scope: 'email,user_photos,user_videos'});

}


function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  function FbLogout()
  {
      FB.logout(function(){document.location.reload();});
  }
  
  function statusChangeCallback(response) {
    if (response.status === 'connected') {
      //testAPI();
    } else if (response.status === 'not_authorized') {
    	console.log('Please log into this app');
    } else {
    	console.log('Please log into facebook');
    }
  }
  
  function testAPI() {
	    FB.api('/me',{fields: 'name,email'}, function(response) {
	    	var name=response.name;
	    	var email = response.email;
	    	$("#loginType").val("facebook");
            $.ajax({
            	url:"facebookRegister",
            	type:"post",
            	data:{
            		"name":name,
            		"email":email
            	},
            	success:function(data){
            		location.reload();
            	},
            	error: function (data) {
            					
				}
            });
	    });
	  }

function onLoadCallback()
{
gapi.client.setApiKey('AIzaSyBHOC33LwvJSEVtXy_k0DCFS_KrpPenW6w'); //set your API KEY
gapi.client.load('plus', 'v1',function(){});//Load Google + API
}

function glogin() 
{
var myParams = {
'clientid' : '727437530076-a5drcojc5vi3jst4kk7549ngino244bm.apps.googleusercontent.com', //You need to set client id
'cookiepolicy' : 'single_host_origin',
'callback' : 'loginCallback', //callback function
'approvalprompt':'force',
'scope' : 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
};
gapi.auth.signIn(myParams);
}


function loginCallback(result)
{
    if(result['status']['signed_in'])
    {
        var request = gapi.client.plus.people.get(
        {
            'userId': 'me'
        });
        request.execute(function (resp)
        {
            var email = '';
            if(resp['emails'])
            {
                for(i = 0; i < resp['emails'].length; i++)
                {
                    if(resp['emails'][i]['type'] == 'account')
                    {
                        email = resp['emails'][i]['value'];
                    }
                }
            }
            
            var name = resp['displayName'];
/*            var imageUrl= resp['image']['url'];
            var url = resp['url'];*/

            
            //idar ajax function call karna hai login ka save karne
            $.ajax({
            	url:"googleRegister",
            	type:"post",
            	data:{
            		"name":name,
            		"email":email
            	},
            	success:function(data){
            		$("#loginType").val("google");
            		location.reload();
            	},
            	error: function (data) {
            					
				}
            });
            
        });
 
    }
 
}

function glogout()
{
	gapi.auth.signOut();
	location.reload();
}