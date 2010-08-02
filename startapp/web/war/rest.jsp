<br />
<a href="index.jsp">Main page</a><br />
<br />
<c:set var="userregisterFull" >
<java class="java.beans.XMLDecoder"> 
 <object class="org.gwtapp.startapp.rpc.data.user.register.UserRegisterModelImpl"> 
  <void property="login"> 
   <string>rolesiak</string> 
  </void> 
  <void property="password"> 
   <string>1234567</string> 
  </void> 
 </object> 
</java> 
</c:set>
<c:set var="userregisterFullForm" >
&nbsp;The result is <i>ID</i>.
<form method="post" action="/rest/startapp">
	<input type="hidden" name="method" value="register"></input>
	<input type="hidden" name="arg" value="<c:out value="${userregisterFull}"></c:out>"></input>
	<input type="hidden" name="type" value="org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel"></input>
	<input type="submit" value="Submit Model"></input>
</form>
</c:set>
<div  style="border: 1px solid gray;padding:5px;">
Argument value (not XML escaped):
<pre>
<c:out value="${userregisterFull}"></c:out>
</pre>
HTML form:
<pre>
<c:out value="${userregisterFullForm}"></c:out>
</pre>
<div style="border: 1px solid gray;padding:5px;width:150px;">${userregisterFullForm}</div>
</div>
<br />
<div style="border: 1px solid gray;padding:5px;">
	The result is <i>null</i>
	<form method="post" action="/rest/startapp">
	<input type="hidden" name="method" value="register"></input>
	<input type="hidden" name="arg" value="NULL"></input>
	<input type="hidden" name="type" value="org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel"></input>
	<input type="submit" value="Submit Null"></input>
	</form>
</div>