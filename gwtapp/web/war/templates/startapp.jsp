<br />
<%if("internal".equals(request.getParameter("type"))){ %>
<div style="background-color:#efef8f;">
<%} else if("external".equals(request.getParameter("type"))){ %>
<div style="background-color:#dfefef;">
<%} else { %>
<div style="background-color:#efefef;">
<%} %>
	@This is HTML template - begin<br />
	<ul>
		<li>Template source - <a href="/templates/startapp.jsp">startapp.jsp</a></li>
		<%if("internal".equals(request.getParameter("type"))){ %>
		<li>GWT Panel - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/InternalTemplatingPanel.java?view=markup">InternalTemplatingPanel.java</a></li>
		<%} %>
		<%if("external".equals(request.getParameter("type"))){ %>
		<li>GWT Panel - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/ExternalTemplatingPanel.java?view=markup">ExternalTemplatingPanel.java</a></li>
		<%} %>
	</ul>
	<div>
		<input template="t1" type="text" style="width:250px;" class="style-t1" value="it should be replaced to the empty field"/>
	</div>
	<div>
		BBB
		<div>
			CCC
			<input template="t2" type="text" style="width:250px;" class="style-t2" value="it should be replaced to the empty field"/>
		</div>
	</div>
	@This is HTML template - end
</div>
