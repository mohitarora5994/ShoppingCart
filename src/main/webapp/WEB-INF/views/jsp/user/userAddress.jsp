<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="userAddress">
<h3>Address</h3>
	<c:forEach var="a" items="${Address}">
		<div style="border-style: solid; width: 222px;">	
			${a.address}
		</div>
<br><br>
</c:forEach>
</div>
<div class="addU">Add New Address</div>
<div id="addUserAddress" class="uadd">
<h4>Add address</h4>
<table>
<form action="saveUserAddress" method="post">
	<tr>
		<td align="right" >Full Name:</td>
		<td><input type="text" size="75" name="name" placeholder="Name on which address need to be saved" ></td>
	</tr>
	<tr>
		<td align="right">Mobile Number:</td>
		<td><input type="text" size="30" name="mobileNo" placeholder="Number on which to call for delivery"></td>
	</tr>
	<tr>
		<td align="right">Pincode:</td>
		<td><input type="text" size="15" name="pincode" ></td>
	</tr>
	<tr>
		<td align="right">Flat / House No. / Floor / Building: </td>
		<td><input type="text" size="75" name="a1" ></td>
	</tr>
	<tr>
		<td align="right">Colony / Street / Locality: </td>
		<td><input type="text" size="75" name="a2" ></td>
	</tr>
	<tr>
		<td align="right">Town/City: </td>
		<td><input type="text" size="75" name="a3" ></td>
	</tr>
	<tr>
		<td align="right">State: </td>
		<td><input type="text" size="75" name="a4" ></td>
	</tr>
	<tr>
		<td align="right" ><input type="reset" value="Reset"></td>
		<td><input type="submit" value="Submit" ></td>
	</tr>
</form>
</table>

</div>