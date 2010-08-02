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

<form method="post" action="/rest/startapp" style="border: 1px solid gray">
<pre>
<c:out value="${userregisterFull}"></c:out>
</pre>
<input type="hidden" name="method" value="register"></input>
<input type="hidden" name="arg" value="<c:out value="${userregisterFull}"></c:out>"></input>
<input type="hidden" name="type" value="org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel"></input>
<input type="submit" value="Submit Full Model"></input>
Submit returns ID.
</form>
<br />
<form method="post" action="/rest/startapp" style="border: 1px solid gray">
<input type="hidden" name="method" value="register"></input>
<input type="hidden" name="arg" value="NULL"></input>
<input type="hidden" name="type" value="org.gwtapp.startapp.rpc.data.user.register.UserRegisterModel"></input>
<input type="submit" value="Submit Null Value"></input>
Submit returns null for null argument.
</form>