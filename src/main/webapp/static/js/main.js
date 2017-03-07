$(document).ready(function(){
	$("#socialLogin").hide();
	$(".uadd").hide();
	$(".viewOrders").hide();
	$(".returnOrder").hide();
	$(".cancelOrder").hide();
	$(".userInfo").hide();
	$(".address").hide();
	$("#checkout").hide();
	$("#company").change(function(){
		submit();
	});
	$(".addU").click(function(){
		$(".uadd").show();
	});
	$(".cancelKPage").click(function(){
		id = $(this).attr("id");
		window.location.href = context+"/user/Order/"+id+"/CancelOrder"
	});
	$(".userUpdate").click(function(){
		$(".userInfo").show();
	});
	$(".CO").click(function(){
		$(".cancelOrder").show();
	});
	$(".oo").click(function(){
		$(".viewOrders").show();
	});
	$(".so").click(function(){
		$(".returnOrder").show();
	});
	
	$("#type").change(function() {
		submit();
	});
	var username = $("#username").val();
	var context = $("#context").val();
	if(username == ""){		
		$(".user").hide();
		$(".logout").hide();
	}else{
		$(".login").hide();
		$(".signin").hide();
	}
	
	$(".login").hover(function(){
		$("#socialLogin").show();
	});
	
	$("#socialLogin").mouseleave(function(){
		$("#socialLogin").hide();
	});
	
    var pay=$('#payy').find(":selected").text();
    $('#paymentSelected').text(pay);
    
    
	$("#home").click(function (e) {
		e.preventDefault();
		window.location.href=context;
	});
	$(".logout").click(function (e) {
		FbLogout();
		glogout();
	});
	var sum = 0;
	$('.subtotal').each(function(){
	    sum += parseFloat($(this).text());
	});
	
	$('.total').text(sum);
	
	$(".RemoveFromCart").click(function(){
		var id = $(this).attr('id');
		$(this).hide();
		$.ajax({
			url:"RemoveFromCart",
			type:"post",
			data:{
				"id":id
			},
			success:function(data){
				$(".message").text(data);
				$(".message").css('background-color', 'black');
				/*$("#body").load(location.href+" #body");*/
				location.reload();
				setTimeout(function() {
			        $(".message").empty();
			    }, 3000);						
			}
		})
	});
	
	$(".cart").click(function(){
		var id = $(this).attr('id');
		$.ajax({
			url:context+"/Cart",
			type:"post",
			data:{
				"id":id
			},
			success:function(data){
				$(".message").text(data);
				$(".message").css('background-color', 'black');
				setTimeout(function() {
			        $(".message").empty();
			    }, 3000);
				
			}
		})
	});
	$(".uOrder").click(function(){
		window.location.href = context+"/user/userOrder";
	});
	$(".wishlist").click(function(){
		var id = $(this).attr('id');
		$.ajax({
			url:context+"/wishlist",
			type:"post",
			data:{
				"id":id
			},
			success: function(data){
				$(".message").text(data);
				$(".message").css('background-color', 'black');
				setTimeout(function() {
			        $(".message").empty();
			    }, 3000);
			}
		})
	});	
	
    $('.qtyplus').click(function(e){
        e.preventDefault();
        id = $(this).attr('id');
        fieldName = $(this).attr('field');
        var price = $(".price"+id).attr('value');
        var currentVal = parseInt($('input[name='+fieldName+']').val());
        if (!isNaN(currentVal)) {
            $('input[name='+fieldName+']').val(currentVal + 1);
            var newprice = price * (currentVal + 1);
            $('#price'+id).text(newprice);
            sum = 0
        	$('.subtotal').each(function(){
        	    sum += parseFloat($(this).text());
        	});
        	$('.total').text(sum);
        } else {
            $('input[name='+fieldName+']').val(1);
        }
    });
    
    $(".qtyminus").click(function(e) {        
        e.preventDefault();   
        id = $(this).attr('id');
        fieldName = $(this).attr('field');  
        var price = $(".price"+id).attr('value');
        var currentVal = parseInt($('input[name='+fieldName+']').val());
        if (!isNaN(currentVal) && currentVal > 1) {
            $('input[name='+fieldName+']').val(currentVal - 1);
            var newprice = price * (currentVal - 1);
            $('#price'+id).text(newprice);
            sum = 0
        	$('.subtotal').each(function(){
        	    sum += parseFloat($(this).text());
        	});
        	$('.total').text(sum);
        } else {
            $('input[name='+fieldName+']').val(1);
        }
    });
	
    $('#order').click(function (){
    	i=0;
    	var k = [];
    	var v=[];
    	var deliver=[];
    	$("input[name=product]").each(function(){
    		k[i]=$(this).attr("field");
    		v[i]=$("input[name=quantity"+k[i]+"]").val();
    		deliver[i]=$(".delivery"+k[i]+"").text();
    		i++;
    	});
    	estimatePrice = $(".total").text();
    	$.ajax({
    		url:'Order',
    		type:"post",
    		data:{
    			'id':k,
    			'quan':v,
    			'total':estimatePrice,
    			'delivery':deliver
    		},
    		success:function(){
    			console.log("success");
    			window.location.href="order";
    		},
    		error:function(){
    			console.log("failure");
    		}
    	});
    });
    
    $('#loading')
    .hide()
    .ajaxStart(function() {
        $(this).show();
    })
    .ajaxStop(function() {
        $(this).hide();
    });
    
     $('.saveAddress').click(function(){
    	 var address = $("#address").val();
    	 $.ajax({
		url:'saveAddress',
		type:"post",
		data:{
			'address':address
		},
		success:function(){
			location.reload();
		},
		error:function(){
			console.log("failure");
		}	
    	});
     });
     
     $('.addressDb').click(function() {
    	 $('.addressDb').css("background", "white");
    	 $(this).css("background", "green");
    	 addressId = $(this).attr('id');
    	 var add=$(this).text();
    	 $('#addressSelected').text(add);
    	 $('#addressId').val(addressId);
    	 $("#checkout").show();
	});
    var i = 1;
     $(".addAddress").click(function() {
    	 if(i==0){
    		 $(".address").hide();
    		 i=1;
    		 }
    	 else{
    	 i=0;
    	 $(".address").show();
    	 }
	});
     
     $("#checkout").click(function() {
    	 add =  $('#addressId').val();
    	 payment = $("#paymentSelected").text();
    	 console.log(payment);
    	 $.ajax({
    			url:'checkout',
    			type:"post",
    			data:{
    				'addressId':add,
    				'payment':payment
    			},
    			success:function(data){
    				window.location.href="Order/"+data;
    			},
    			error:function(){
    				console.log("failure");
    			}	
    	    	});
	});
    
     $('#payy').on('change', function() {
    	  pay=$('#payy').find(":selected").text();
    	    $('#paymentSelected').text(pay);
    	});
     
     
     $('.product').click(function(){
    	 id = $(this).attr('id');
    	 window.location.href = context+"/product/"+id;
     });
     
     $('.user').click(function(){
    	 window.location.href = context+"/user";
     });
     
     $('.seller').click(function(){
    	 window.location.href = context+"/seller/sellerInfo";
     });
     
 	$(".orderKPage").click(function(){
		id = $(this).attr('id');
		window.location.href=context+"/user/Order/"+id;
	});
     
});





function submit(){
	$("#searchProducts").submit();
}






