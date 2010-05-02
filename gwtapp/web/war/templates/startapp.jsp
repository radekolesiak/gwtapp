<%@taglib tagdir="/WEB-INF/tags" prefix="t"%>
<t:template tag="b" style="color:#336633;" styleclass="startapp-template">
	<div style="background-color: #efefef;width:325px;">
		@This is HTML template - begin ${param.name}<br />		
		<ul>
			<li>Form Template - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/templates/startapp.jsp?view=markup">startapp.jsp</a> (<a href="/templates/startapp.jsp">view</a>)</li>
			<li>Login Field Template - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/war/templates/login.jsp?view=markup">login.jsp</a> (<a href="/templates/login.jsp">view</a>)</li>
			<li>UI Panel 1 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/StartApp.java?view=markup">StartApp.java</a></li>
			<li>UI Panel 2 - <a href="http://gwtapp.svn.sourceforge.net/viewvc/gwtapp/trunk/gwtapp/web/src/main/java/org/gwtapp/startapp/client/ui/StartAppTemplatePanel.java?view=markup\">StartAppTemplatePanel.java</a></li>
		</ul>
		<div class="tablediv">
			<div class="rowdiv" t:field="login"></div>
			<div class="rowdiv">
				<div class="celldiv1">Password:</div>
				<div class="celldiv2"><input t:field="password" type="text" style="width: 250px;" class="style-t2" value="it should be replaced to the empty field" /></div>
			</div>
		</div>
		@This is HTML template - end
	</div>
</t:template>