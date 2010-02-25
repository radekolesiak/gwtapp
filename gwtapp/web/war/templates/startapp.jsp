<%@ taglib uri="/template.tld" prefix="t" %>
<%@page import="org.gwtapp.startapp.client.data.user.register.UserRegister"%>
<t:template tag="p">
	<%if("internal".equals(request.getParameter("type"))){ %>
	<div style="background-color:#efef8f;">
	<%} else if("external".equals(request.getParameter("type"))){ %>
	<div style="background-color:#dfefef;">
	<%} else { %>
	<div style="background-color:#efefef;">
	<%} %>
		@This is HTML template - begin<br />
		<ul>
			<li>Template - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/templates/startapp.jsp?view=markup">startapp.jsp</a> (<a href="/templates/startapp.jsp">view</a>)</li>
			<li>UI Panel 1 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
			<%if("internal".equals(request.getParameter("type"))){ %>
			<li>UI Panel 2 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/InternalTemplatingPanel.java?view=markup">InternalTemplatingPanel.java</a></li>
			<%} %>
			<%if("external".equals(request.getParameter("type"))){ %>
			<li>UI Panel 2 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/ExternalTemplatingPanel.java?view=markup">ExternalTemplatingPanel.java</a></li>
			<%} %>
		</ul>
		<div>
			<input template="<%=UserRegister.LOGIN.name()%>" type="text" style="width:250px;" class="style-t1" value="it should be replaced to the empty field"/>
		</div>
		<div>
			BBB
			<div>
				CCC
				<input template="<%=UserRegister.PASSWORD.name()%>" type="text" style="width:250px;" class="style-t2" value="it should be replaced to the empty field"/>
			</div>
		</div>
		@This is HTML template - end
	</div>
</t:template>