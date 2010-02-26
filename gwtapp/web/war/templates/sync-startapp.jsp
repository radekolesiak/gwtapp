<%@ taglib uri="/template.tld" prefix="t"%>
<%@page import="org.gwtapp.startapp.client.data.user.register.UserRegister"%>
<t:template tag="b" style="color:#336633;" styleclass="startapp-template">
	<div style="background-color: #efefef;">
		@This is sync HTML template - begin<br />
		<ul>
			<li>Template - <a
				href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/templates/startapp.jsp?view=markup">startapp.jsp</a>
			(<a href="/templates/startapp.jsp">view</a>)</li>
			<li>UI Panel 1 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
			<li>UI Panel 2 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/SyncRepositoryTemplatingPanel.java?view=markup\">SyncRepositoryTemplatingPanel.java</a></li>
		</ul>
		<div>
			<input template="login" type="text" style="width: 250px;" class="style-t1" value="it should be replaced to the empty field" /><br /> 
			<input template="password" type="text" style="width: 250px;" class="style-t2" value="it should be replaced to the empty field" /><br />
		</div>
		@This is sync HTML template - end
	</div>
</t:template>